package com.example.diplom.dto;

import com.example.diplom.entity.MetalCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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

    private double costPrice;

    private double markup;

    private double finalPrice;

    private MetalCategory metalCategory;
}
