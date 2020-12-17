package com.gamary.paymentrequestservice.service.impl;

import com.gamary.paymentrequestservice.entity.PaymentRequest;
import com.gamary.paymentrequestservice.exception.ResourceNotFoundException;
import com.gamary.paymentrequestservice.repository.PaymentRequestRepository;
import com.gamary.paymentrequestservice.service.PaymentRequestService;
import com.gamary.paymentrequestservice.service.dto.PaymentRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentRequestServiceImplTest {

    private static final Long ID = 1L;
    private static final Integer AMOUNT_IN_CENTS = 100;
    private static final String DESCRIPTION = "Description";
    private static final LocalDate EXPIRY_DATE = LocalDate.now().plusYears(1l);

    @Mock
    private KafkaProducer kafkaProducer;
    @Mock
    private PaymentRequestRepository paymentRequestRepository;
    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private PaymentRequestServiceImpl paymentRequestService;

    @Test
    public void testGetExistingPaymentRequest() throws ResourceNotFoundException {
        when(paymentRequestRepository.findById(any(Long.class))).thenReturn(Optional.of(PaymentRequest.builder().
                id(ID).amountInCents(AMOUNT_IN_CENTS).description(DESCRIPTION).expiryDate(EXPIRY_DATE).build()));

        PaymentRequestDTO paymentRequestDTO = paymentRequestService.getPaymentRequest(ID);

        assertThat(paymentRequestDTO.getId()).isEqualTo(ID);
        assertThat(paymentRequestDTO.getAmountInCents()).isEqualTo(AMOUNT_IN_CENTS);
        assertThat(paymentRequestDTO.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(paymentRequestDTO.getExpiryDate()).isEqualTo(EXPIRY_DATE);

    }

    @Test
    public void testGetNotExistingPaymentRequest() throws ResourceNotFoundException {
        when(paymentRequestRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> paymentRequestService.getPaymentRequest(ID)
        );
    }



    @Test
    public void testCreatePaymentRequest(){
        PaymentRequestDTO paymentRequestDTO = PaymentRequestDTO.builder().amountInCents(AMOUNT_IN_CENTS).
                description(DESCRIPTION).expiryDate(EXPIRY_DATE).build();
        when(paymentRequestRepository.save(any())).thenReturn(PaymentRequest.builder().
                id(ID).amountInCents(AMOUNT_IN_CENTS).description(DESCRIPTION).expiryDate(EXPIRY_DATE).build());
        PaymentRequestDTO response = paymentRequestService.createPaymentRequest(paymentRequestDTO);

        assertThat(response.getId()).isEqualTo(ID);
        assertThat(response.getAmountInCents()).isEqualTo(AMOUNT_IN_CENTS);
        assertThat(response.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(response.getExpiryDate()).isEqualTo(EXPIRY_DATE);
    }

}
