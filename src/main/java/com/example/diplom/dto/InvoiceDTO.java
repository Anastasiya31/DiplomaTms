package com.example.diplom.dto;

import com.example.diplom.entity.Company;
import com.example.diplom.entity.InvoiceType;
import com.example.diplom.entity.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Date data;

    private String paymentDetails;

    private Order order;

    private Company company;

    private InvoiceType invoiceType;
}
