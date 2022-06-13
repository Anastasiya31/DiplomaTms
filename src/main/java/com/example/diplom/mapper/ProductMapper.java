package com.example.diplom.mapper;

import com.example.diplom.dto.ProductDTO;
import com.example.diplom.entity.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {
    public Product productDtoToProduct(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .grade(productDTO.getGrade())
                .metalCategory(productDTO.getMetalCategory())
                .type(productDTO.getType())
                .costPrice(productDTO.getCostPrice())
                .markup(productDTO.getMarkup())
                .finalPrice(productDTO.getFinalPrice())
                .company(productDTO.getCompany())
                .build();
    }

    public ProductDTO productToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .grade(product.getGrade())
                .metalCategory(product.getMetalCategory())
                .type(product.getType())
                .costPrice(product.getCostPrice())
                .markup(product.getMarkup())
                .finalPrice(product.getFinalPrice())
                .company(product.getCompany())
                .build();
    }

}