package com.gamary.paymentrequestservice.web.rest;

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
    public ResponseEntity<?> createPaymentRequest(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO){
        if(paymentRequestDTO.getId()!=null)
            return ResponseEntity.badRequest().body("Payment Request is not valid");

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentRequestService.createPaymentRequest(paymentRequestDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentRequest(@PathVariable Long id){
        return ResponseEntity.ok(paymentRequestService.getPaymentRequest(id));
    }
}
