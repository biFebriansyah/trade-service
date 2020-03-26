package com.finaltest.tradeservice.service;

import java.util.List;

import com.finaltest.tradeservice.dto.TradesDto;
import com.finaltest.tradeservice.dto.test;
import com.finaltest.tradeservice.exception.TradeException;
import com.finaltest.tradeservice.model.TradesModel;

public interface _tradeService {

    List<String> test(test data);
    TradesModel Transaction(TradesDto data) throws TradeException;
    List<TradesModel> get();
    TradesModel getById(long id);
    TradesModel getByWhat(String trsactionDate, String costumerEmail, String transactionNumber) throws TradeException;
}
