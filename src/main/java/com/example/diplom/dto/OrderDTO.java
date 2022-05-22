package com.example.diplom.dto;

import com.example.diplom.entity.Currency;
import com.example.diplom.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Long id;

    private BigDecimal price;

    private double weight;

    private Product product;

    private Currency currency;

}
