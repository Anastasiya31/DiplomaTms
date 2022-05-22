package com.example.diplom;

import com.example.diplom.dto.ProductDTO;
import com.example.diplom.entity.MetalCategory;
import com.example.diplom.entity.Product;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.mapper.ProductMapper;
import com.example.diplom.service.ProductService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {
    static ProductService productService;

    @ParameterizedTest
    @DisplayName("Testing search Product which exists in database")
    @Tag("Product-TEST")
    @ValueSource(ints = {1})
    void FindProductByIdExists() throws EntityNotFountException {
        Long id = 1L;
        ProductDTO actual = ProductMapper.productToProductDTO(productService.getProductById(1L));
        ProductDTO expected = new ProductDTO();
        Assertions.assertAll(() -> {
            Assertions.assertEquals(expected.getType(), actual.getType());
            Assertions.assertEquals(expected.getGrade(), actual.getGrade());
        });
    }

    @Test
    @DisplayName("Testing search Product which non exists in database")
    @Tag("Product-TEST")
    void FindProductByIdNotExists() throws EntityNotFountException {
        Long id = 1L;
        ProductDTO actual = ProductMapper.productToProductDTO(productService.getProductById(1L));
        ProductDTO expected = new ProductDTO(1L, "111", "111", new MetalCategory());
        Assertions.assertAll(() -> {
            Assertions.assertNotEquals(expected.getType(), actual.getType());
            Assertions.assertNotEquals(expected.getGrade(), actual.getGrade());
        });
    }

    @Test
    @DisplayName("Testing saving Product success")
    void testSaveProductSuccess() {
        Product expected = new Product(1L, "111", "111", new MetalCategory());
        Product actualProduct = productService.createProduct(ProductMapper.productToProductDTO(expected));
        Assertions.assertEquals(expected, actualProduct);
    }

    @Test
    @DisplayName("Testing saving Product failed")
    void testSaveProductFailed() {
        Product expected = new Product(1L, "111", "111", new MetalCategory());
        Product actualProduct = productService.createProduct(ProductMapper.productToProductDTO(expected));
        Assertions.assertNotEquals(expected, actualProduct);
    }

    @Test
    @DisplayName("Testing updating Product success")
    void testUpdateProductSuccess() throws EntityNotFountException {
        Long id = 1L;
        Product expected = new Product(1L, "111", "111", new MetalCategory());
        productService.updateProduct(1L, ProductMapper.productToProductDTO(expected));
        Product actualProduct = productService.getProductById(id);
        Assertions.assertEquals(expected, actualProduct);
    }

    @Test
    @DisplayName("Testing updating Product failed")
    void testUpdateProductFailed() throws EntityNotFountException {
        Long id = 100L;
        Product expected = new Product(1L, "111", "111", new MetalCategory());
        productService.updateProduct(1L, ProductMapper.productToProductDTO(expected));
        Product actualProduct = productService.getProductById(id);
        Assertions.assertNotEquals(expected, actualProduct);
    }

    @Test
    @DisplayName("Testing delete Product which exists in database")
    @Tag("Product-TEST")
    void testDeleteProductSuccess() throws EntityNotFountException {
        Long id = 1L;
        productService.deleteProduct(id);
        Product actualProduct = productService.getProductById(1L);
        Assertions.assertNull(actualProduct);
    }

    @Test
    @DisplayName("Testing delete Product which exists in database")
    @Tag("Product-TEST")
    void testDeleteProductFailed() throws EntityNotFountException {
        Long id = 1L;
        productService.deleteProduct(id);
        Product actualProduct = productService.getProductById(id);
        Assertions.assertNotEquals(null, actualProduct);
    }

}
