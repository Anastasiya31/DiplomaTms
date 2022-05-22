package com.example.diplom;

import com.example.diplom.dto.MetalCategoryDTO;
import com.example.diplom.entity.MetalCategory;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.mapper.MetalCategoryMapper;
import com.example.diplom.service.MetalCategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MetalCategoryTest {
    static MetalCategoryService metalCategoryService;

    @ParameterizedTest
    @DisplayName("Testing search MetalCategory which exists in database")
    @Tag("MetalCategoryS-TEST")
    @ValueSource(ints = {1})
    void FindMetalCategoryByIdExists() throws EntityNotFountException {
        Long id = 1L;
        MetalCategoryDTO actual = MetalCategoryMapper.metalCategoryToMetalCategoryDTO(metalCategoryService.getMetalCategoryById(1L));
        MetalCategoryDTO expected = new MetalCategoryDTO();
        Assertions.assertAll(()->{
            Assertions.assertEquals(expected.getCategory(),actual.getCategory());
        });
    }

    @Test
    @DisplayName("Testing search MetalCategory which non exists in database")
    @Tag("MetalCategory-TEST")
    void FindMetalCategoryByIdNotExists() throws EntityNotFountException {
        Long id = 1L;
        MetalCategoryDTO actual = MetalCategoryMapper.metalCategoryToMetalCategoryDTO(metalCategoryService.getMetalCategoryById(1L));
        MetalCategoryDTO expected = new MetalCategoryDTO(1L, "www");
        Assertions.assertAll(()->{
            Assertions.assertNotEquals(expected.getCategory(),actual.getCategory());

        });
    }

    @Test
    @DisplayName("Testing saving MetalCategory success")
    void testSaveMetalCategorySuccess() {
        MetalCategory expected = new MetalCategory(1L,"www");
        MetalCategory actualMetalCategory = metalCategoryService.createMetalCategory(MetalCategoryMapper.metalCategoryToMetalCategoryDTO(expected));
        Assertions.assertEquals(expected, actualMetalCategory);
    }

    @Test
    @DisplayName("Testing saving MetalCategory failed")
    void testSaveMetalCategoryFailed() {
        MetalCategory expected = new MetalCategory(1L, "www");
        MetalCategory actualMetalCategory = metalCategoryService.createMetalCategory(MetalCategoryMapper.metalCategoryToMetalCategoryDTO(expected));
        Assertions.assertNotEquals(expected, actualMetalCategory);
    }

    @Test
    @DisplayName("Testing updating MetalCategory success")
    void testUpdateMetalCategorySuccess() throws EntityNotFountException {
        Long id = 1L;
        MetalCategory expected = new MetalCategory(1L,"www");
        metalCategoryService.updateMetalCategory(1L,MetalCategoryMapper.metalCategoryToMetalCategoryDTO(expected));
        MetalCategory actualMetalCategory =  metalCategoryService.getMetalCategoryById(id);
        Assertions.assertEquals(expected, actualMetalCategory);
    }

    @Test
    @DisplayName("Testing updating MetalCategory failed")
    void testUpdateMetalCategoryFailed() throws EntityNotFountException {
        Long id = 100L;
        MetalCategory expected = new MetalCategory(1L,"www");
        metalCategoryService.updateMetalCategory(1L,MetalCategoryMapper.metalCategoryToMetalCategoryDTO(expected));
        MetalCategory actualMetalCategory =  metalCategoryService.getMetalCategoryById(id);
        Assertions.assertNotEquals(expected, actualMetalCategory);
    }

    @Test
    @DisplayName("Testing delete MetalCategory which exists in database")
    @Tag("MetalCategory-TEST")
    void testDeleteMetalCategorySuccess() throws EntityNotFountException {
        Long id = 1L;
        metalCategoryService.deleteMetalCategory(id);
        MetalCategory actualMetalCategory = metalCategoryService.getMetalCategoryById(1L);
        Assertions.assertNull(actualMetalCategory);
    }
    @Test
    @DisplayName("Testing delete MetalCategory which exists in database")
    @Tag("MetalCategory-TEST")
    void testDeleteMetalCategoryFailed() throws EntityNotFountException {
        Long id = 1L;
        metalCategoryService.deleteMetalCategory(id);
        MetalCategory actualMetalCategory = metalCategoryService.getMetalCategoryById(id);
        Assertions.assertNotEquals(null,actualMetalCategory);
    }
}
