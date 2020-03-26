package com.finaltest.tradeservice.controller;

import com.finaltest.tradeservice.dto.TradesDto;
import com.finaltest.tradeservice.exception.TradeException;
import com.finaltest.tradeservice.service._TradesLogServices;
import com.finaltest.tradeservice.service._tradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.finaltest.tradeservice.dto.test;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class TradesController {

    @Autowired
    _tradeService tradeService;

    @Autowired
    _TradesLogServices tradesLogServices;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity Check(@RequestBody TradesDto data) {
        try {
            return new ResponseEntity(tradeService.Transaction(data), HttpStatus.OK);
        } catch (TradeException err) {
            return new ResponseEntity(err.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity getTradeByWhat (@RequestParam(required = false) String trsactionDate, @RequestParam(required = false) String costumerEmail, @RequestParam(required = false) String transactionNumber) {
        try {
            if (trsactionDate == null && costumerEmail == null && transactionNumber == null) {
                return new ResponseEntity(tradeService.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity(tradeService.getByWhat(trsactionDate, costumerEmail, transactionNumber), HttpStatus.OK);
            }
        } catch (TradeException err) {
            return new ResponseEntity(err.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/logs")
    public ResponseEntity getLogs (@RequestParam(required = false) String email, @RequestParam(required = false) String status) {

        if (email == null) {
            return new ResponseEntity(tradesLogServices.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(tradesLogServices.getByEmail(email, status), HttpStatus.OK);
        }
    }
}
