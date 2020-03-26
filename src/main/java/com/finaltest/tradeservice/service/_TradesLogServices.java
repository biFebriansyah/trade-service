package com.finaltest.tradeservice.service;

import com.finaltest.tradeservice.model.TradesLog;

import java.util.List;

public interface _TradesLogServices {

    List<TradesLog> get();
    List<TradesLog> getByEmail(String email, String status);
}
