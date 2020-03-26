package com.finaltest.tradeservice.service;


import com.finaltest.tradeservice.model.TradesLog;
import com.finaltest.tradeservice.repo._TradesLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class TradesLogServices implements _TradesLogServices {

    @Autowired
    _TradesLogs tradesLogs;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TradesLog> get() {
        List<TradesLog> logs = new ArrayList<>();
        tradesLogs.findAll().iterator().forEachRemaining(logs::add);
        return logs;
    }

    @Override
    public List<TradesLog> getByEmail(String email, String status) {
        Query query = null;
        if (status == null) {
            query = entityManager.createQuery("FROM TradesLog WHERE email = :email").setParameter("email", email);
        } else {
            query = entityManager.createQuery("FROM TradesLog WHERE email = :email AND status = :status").setParameter("email", email).setParameter("status", status);
        }
        List<TradesLog> data = query.getResultList();
        return data;
    }
}
