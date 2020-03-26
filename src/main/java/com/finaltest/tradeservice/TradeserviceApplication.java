package com.finaltest.tradeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TradeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeserviceApplication.class, args);
	}

}
