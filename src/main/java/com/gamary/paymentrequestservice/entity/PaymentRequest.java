package com.gamary.paymentrequestservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaymentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amountInCents;

    @NotBlank
    @Size(max = 35)
    private String description;

    private LocalDate expiryDate;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Long objId = ((PaymentRequest) obj).id;

        return objId==null? false:Objects.equals(id,objId);
    }
}
