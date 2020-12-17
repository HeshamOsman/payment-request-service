package com.gamary.paymentrequestservice.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
