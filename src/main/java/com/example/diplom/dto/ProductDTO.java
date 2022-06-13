package com.example.diplom.dto;

import com.example.diplom.entity.MetalCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String type;

    private String grade;

    private BigDecimal costPrice;

    private double markup;

    private BigDecimal finalPrice;

    private MetalCategory metalCategory;

    private String company;
}
