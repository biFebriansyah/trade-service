package com.finaltest.tradeservice.service;

import com.finaltest.tradeservice.dto.ConsumerDto;
import com.finaltest.tradeservice.model.TradesModel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TradeSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Value("${finaltest.rabbitmq.exchange}")
    private String exchange;

    @Value("${finaltest.rabbitmq.routingkey}")
    private String routingkey;

    public void SendData(ConsumerDto model) {
        amqpTemplate.convertAndSend(exchange, routingkey, model);
    }
}
