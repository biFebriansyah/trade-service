package com.finaltest.tradeservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finaltest.tradeservice.dto.ConsumerDto;
import com.finaltest.tradeservice.dto.TradesDto;
import com.finaltest.tradeservice.exception.TradeException;
import com.finaltest.tradeservice.model.TradesLog;
import com.finaltest.tradeservice.model.TradesModel;
import com.finaltest.tradeservice.repo._TradesLogs;
import com.finaltest.tradeservice.repo._TradesRepo;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finaltest.tradeservice.dto.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TradeService implements _tradeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    _TradesRepo tradesRepo;

    @Autowired
    _TradesLogs tradesLogs;

    @Autowired
    TradeSender tradeSender;

    @Override
    public List<String> test(test data) {
        List<String> test = new ArrayList<>();
        int amount = 0;
        for (Map<String, Object> s : data.getListItem()) {
            int v = (int) s.get("sellingPrice");
            int x = (int) s.get("quantity");
            int i = v * x ;
            amount = amount + i;
        }
        System.out.println(amount);
        return test;
    }

    public Long toalDiscount(String persent, int bruto) {
        try {
            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            Number number = numberFormat.parse(persent);
            float value = bruto * number.floatValue();
            return new Long((int) value);
        } catch (ParseException err) {
            return null;
        }
    }

    @Override
    public TradesModel Transaction(TradesDto data) throws TradeException {
        if (data.getPaymentMethod().equals("Cash") || data.getPaymentMethod().equals("CASH")) {
            if (data.getPaymentAmount() == 0) {
                throw new TradeException("Pyamount Amount Can't be null");
            }
        }
        TradesModel model = new TradesModel();
        ConsumerDto consumerDto = new ConsumerDto();
        int amount = 0;
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter numberFormater = DateTimeFormatter.ofPattern("ddMMyyyy");
        int number = Integer.parseInt(date.format(numberFormater));
        for (Map<String, Object> s : data.getListItem()) {
            int v = (int) s.get("sellingPrice");
            int x = (int) s.get("quantity");
            int i = v * x ;
            amount = amount + i;
        }
        long DiscountAmpunt = toalDiscount(data.getDiscount(), amount);
        long TaxAndSericeFree = amount - DiscountAmpunt;
        long total = (amount - DiscountAmpunt) + (TaxAndSericeFree * 2);
        model.setTransactionNumber(new Long(number));
        model.setTransactionDate(date.format(dateTimeFormatter));
        model.setCustomerEmail(data.getCustomerEmail());
        model.setPaymentMethod(data.getPaymentMethod());
        model.setTotalBruto(new Long(amount));
        model.setDiscountAmpunt(DiscountAmpunt);
        model.setTax(TaxAndSericeFree);
        model.setServiceFee(TaxAndSericeFree);
        model.setTotalPayment(total);
        tradesRepo.save(model);

        consumerDto.setCustomerEmail(data.getCustomerEmail());
        consumerDto.setTransactionDate(date.format(dateTimeFormatter));
        consumerDto.setTransactionNumber(new Long(number));
        consumerDto.setTotalPayment(total);
        tradeSender.SendData(consumerDto);

        TradesLog logs = new TradesLog();
        logs.setEmail(data.getCustomerEmail());
        logs.setDate(date.format(dateTimeFormatter));
        logs.setStatus(TradesLog.Succsess);
        logs.setTradesModel(model);
        tradesLogs.save(logs);

        return model;
    }

    @Override
    public List<TradesModel> get() {
        List<TradesModel> data = new ArrayList<>();
        tradesRepo.findAll().iterator().forEachRemaining(data::add);
        return data;
    }

    @Override
    public TradesModel getById(long id) {
        return tradesRepo.findById(id).get();
    }

    @Override
    public TradesModel getByWhat(String trsactionDate, String costumerEmail, String transactionNumber) throws TradeException {
        Query query = null;
        if (trsactionDate != null && costumerEmail == null && transactionNumber == null) {
            query = entityManager.createQuery("From TradesModel WHERE transactionDate = :transactionDate").setParameter("transactionDate", trsactionDate);
        }
        else if (costumerEmail != null && trsactionDate == null && transactionNumber == null) {
            query = entityManager.createQuery("From TradesModel WHERE customerEmail = :customerEmail").setParameter("customerEmail", costumerEmail);
        }
        else if (transactionNumber != null && trsactionDate == null && costumerEmail == null) {
            query = entityManager.createQuery("From TradesModel WHERE transactionNumber = :transactionNumber").setParameter("transactionNumber", new Long(transactionNumber));

        } else { throw new TradeException("Problem With Parameter"); }

        List<TradesModel> respon = query.getResultList();
        if (respon.size() > 0) {
            TradesModel aM = respon.get(0);
            return aM;
        } else {
            return null;
        }
    }
}
