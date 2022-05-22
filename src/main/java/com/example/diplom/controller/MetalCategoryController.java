package com.example.diplom.controller;

import com.example.diplom.dto.MetalCategoryDTO;
import com.example.diplom.entity.MetalCategory;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.service.MetalCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/metalCategory")
public class MetalCategoryController {
    private final MetalCategoryService metalCategoryService;

    @GetMapping("/metalCategories")
    public List<MetalCategoryDTO> getMetalCategoryService() {
        return metalCategoryService.getMetalCategories();
    }

    @PostMapping("/metalCategories")
    public MetalCategory createMetalCategory(@RequestBody MetalCategoryDTO metalCategoryDTO) {
        return metalCategoryService.createMetalCategory(metalCategoryDTO);
    }

    @PutMapping("/metalCategories/{id}")
    public MetalCategory updateMetalCategory(@PathVariable("id") Long id,
                                             @RequestBody MetalCategoryDTO metalCategoryDTO) {
        return metalCategoryService.updateMetalCategory(id, metalCategoryDTO);
    }

    @DeleteMapping("/metalCategories/{id}")
    public void deleteMetalCategory(@PathVariable("id") Long id) {
        metalCategoryService.deleteMetalCategory(id);
    }

    @GetMapping("/metalCategories/{id}")
    public MetalCategory getById(@PathVariable("id") Long id) throws EntityNotFountException {
        return metalCategoryService.getMetalCategoryById(id);
    }
}
