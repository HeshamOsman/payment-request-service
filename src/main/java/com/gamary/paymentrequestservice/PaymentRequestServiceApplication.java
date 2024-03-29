package com.gamary.paymentrequestservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PaymentRequestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentRequestServiceApplication.class, args);
	}

}
