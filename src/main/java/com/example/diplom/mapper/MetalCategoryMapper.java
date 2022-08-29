package com.example.diplom.mapper;

import com.example.diplom.dto.MetalCategoryDTO;
import com.example.diplom.entity.MetalCategory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MetalCategoryMapper {
    public MetalCategory metalCategoryDtoToMetalCategory(MetalCategoryDTO metalCategoryDTO) {
        return MetalCategory.builder()
                .id(metalCategoryDTO.getId())
                .category(metalCategoryDTO.getCategory())
                .build();
    }

    public MetalCategoryDTO metalCategoryToMetalCategoryDTO(MetalCategory metalCategory) {
        return MetalCategoryDTO.builder()
                .id(metalCategory.getId())
                .category(metalCategory.getCategory())
                .build();
    }

}