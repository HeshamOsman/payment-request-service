package com.gamary.paymentrequestservice.service.impl;

import com.gamary.paymentrequestservice.entity.PaymentRequest;
import com.gamary.paymentrequestservice.exception.ResourceNotFoundException;
import com.gamary.paymentrequestservice.repository.PaymentRequestRepository;
import com.gamary.paymentrequestservice.service.PaymentRequestService;

import com.gamary.paymentrequestservice.service.dto.PaymentRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentRequestServiceImpl implements PaymentRequestService {

    private final PaymentRequestRepository paymentRequestRepository;
    private final ModelMapper mapper;
    private final KafkaProducer kafkaProducer;

    public PaymentRequestServiceImpl(PaymentRequestRepository paymentRequestRepository, KafkaProducer kafkaProducer,ModelMapper mapper) {
        this.paymentRequestRepository = paymentRequestRepository;
        this.kafkaProducer = kafkaProducer;
        this.mapper = mapper;
    }

    @Override
    public PaymentRequestDTO createPaymentRequest(PaymentRequestDTO paymentRequestDTO) {

        paymentRequestDTO = mapper.map(paymentRequestRepository.save(mapper.map(paymentRequestDTO, PaymentRequest.class)),PaymentRequestDTO.class);

        kafkaProducer.sendMessage(paymentRequestDTO);

        return paymentRequestDTO;
    }

    @Override
    public PaymentRequestDTO getPaymentRequest(Long id) throws ResourceNotFoundException {
        return mapper.map(paymentRequestRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException(PaymentRequest.class.getSimpleName(),String.valueOf(id))
        ),PaymentRequestDTO.class);
    }
}
