package com.gamary.paymentrequestservice.repository;

import com.gamary.paymentrequestservice.entity.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest,Long> {
}
