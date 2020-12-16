package com.gamary.paymentrequestservice.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PaymentRequestDTO {

    private Long id;

    private Integer amountInCents;

    @NotBlank
    @Size(max = 35)
    private String description;

    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;


}
