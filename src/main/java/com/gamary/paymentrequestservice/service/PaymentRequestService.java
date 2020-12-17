package com.gamary.paymentrequestservice.service;


import com.gamary.paymentrequestservice.exception.ResourceNotFoundException;
import com.gamary.paymentrequestservice.service.dto.PaymentRequestDTO;

public interface PaymentRequestService {

    PaymentRequestDTO createPaymentRequest(PaymentRequestDTO paymentRequestDTO);
    PaymentRequestDTO getPaymentRequest(Long id) throws ResourceNotFoundException;

}
