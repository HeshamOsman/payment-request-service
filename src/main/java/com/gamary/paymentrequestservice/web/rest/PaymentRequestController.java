package com.gamary.paymentrequestservice.web.rest;

import com.gamary.paymentrequestservice.exception.PostRequestWithIdException;
import com.gamary.paymentrequestservice.exception.ResourceNotFoundException;
import com.gamary.paymentrequestservice.service.PaymentRequestService;
import com.gamary.paymentrequestservice.service.dto.PaymentRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payment-requests")
public class PaymentRequestController {

    private final PaymentRequestService paymentRequestService;

    public PaymentRequestController(PaymentRequestService paymentRequestService) {
        this.paymentRequestService = paymentRequestService;
    }

    @PostMapping
    public ResponseEntity<PaymentRequestDTO> createPaymentRequest(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) throws PostRequestWithIdException {
        if(paymentRequestDTO.getId()!=null)
            throw new PostRequestWithIdException(PaymentRequestDTO.class.getSimpleName());

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentRequestService.createPaymentRequest(paymentRequestDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentRequestDTO> getPaymentRequest(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(paymentRequestService.getPaymentRequest(id));
    }
}
