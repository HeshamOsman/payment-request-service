package com.gamary.paymentrequestservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

        private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
        private static final String TOPIC = "payment-requests-topic";

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;
        @Autowired
        private ObjectMapper objectMapper;

        public void sendMessage(Object message) {
            String messageString=null;
            try {
                messageString = objectMapper.writeValueAsString(message);
            } catch (JsonProcessingException e) {
                logger.info(String.format("Can not parse, with error -> %s",e.getMessage()));
            }
            logger.info(String.format("#### -> Producing message -> %s", messageString));
            this.kafkaTemplate.send(TOPIC, messageString);
        }

}
