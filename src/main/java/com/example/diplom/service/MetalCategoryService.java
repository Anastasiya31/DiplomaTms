package com.example.diplom.service;

import com.example.diplom.dto.MetalCategoryDTO;
import com.example.diplom.entity.MetalCategory;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.mapper.MetalCategoryMapper;
import com.example.diplom.repository.MetalCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetalCategoryService {
    private final MetalCategoryRepository metalCategoryRepository;

    public List<MetalCategoryDTO> getMetalCategories() {
        return metalCategoryRepository.findAll()
                .stream().map(MetalCategoryMapper::metalCategoryToMetalCategoryDTO).collect(Collectors.toList());
    }

    public MetalCategory createMetalCategory(MetalCategoryDTO metalCategoryDTO) {
        return metalCategoryRepository.save(MetalCategoryMapper.metalCategoryDtoToMetalCategory(metalCategoryDTO));
    }

    public MetalCategory updateMetalCategory(Long id, MetalCategoryDTO metalCategoryDTO) {
        return metalCategoryRepository.findById(id)
                .map(metalCategory -> {
                    MetalCategoryMapper.metalCategoryDtoToMetalCategory(metalCategoryDTO);
                    return metalCategoryRepository.save(metalCategory);
                })
                .orElseGet(() -> {
                    metalCategoryDTO.setId(id);
                    return metalCategoryRepository.save(MetalCategoryMapper.metalCategoryDtoToMetalCategory(metalCategoryDTO));
                });
    }

    public void deleteMetalCategory(Long id) {
metalCategoryRepository.deleteById(id);
    }

    public MetalCategory getMetalCategoryById(Long id) throws EntityNotFountException {
        MetalCategory metalCategory;
        Optional<MetalCategory> metalCategoryOptional = metalCategoryRepository.findById(id);
        if (metalCategoryOptional.isPresent()) {
            metalCategory = metalCategoryOptional.get();
        } else {
            throw new EntityNotFountException("MetalCategory with id: " + id + " was not found");
        }
        return metalCategory;
    }
}
