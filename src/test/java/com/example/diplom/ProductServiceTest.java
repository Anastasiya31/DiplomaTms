package com.example.diplom;

import com.example.diplom.dto.ProductDTO;
import com.example.diplom.entity.Product;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.mapper.ProductMapper;
import com.example.diplom.repository.ProductRepository;
import com.example.diplom.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setup() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
        product = Product.builder()
                .company("ff")
                .type("aa")
                .grade("222")
                .markup(5)
                .costPrice(BigDecimal.valueOf(333))
                .finalPrice(BigDecimal.valueOf(313))
                .build();
    }

    @Test
    @DisplayName("Testing saving Product success")
    void testSaveProductSuccess() {

        given(productRepository.save(product)).willReturn(product);
        // when -  action or the behaviour that we are going test
        Product savedProduct = productService.createProduct(ProductMapper.productToProductDTO(product));
        // then - verify the output
        assertThat(savedProduct).isNotNull();
    }
//
    @DisplayName("JUnit test for saveProduct method which throws exception")
    @Test
    public void givenExistingEmail_whenSaveProduct_thenThrowsException() {
        // given - precondition or setup
        given(productRepository.findById(product.getId()))
                .willReturn(Optional.of(product));
        // when -  action or the behaviour that we are going test
        Assertions.assertThrows(EntityNotFountException.class, () -> {
            productService.createProduct(ProductMapper.productToProductDTO(product));
        });
        // then
        verify(productRepository, never()).save(any(Product.class));
    }

    @DisplayName("JUnit test for getAllProducts method")
    @Test
    public void givenProductsList_whenGetAllProducts_thenReturnProductsList() {
        // given - precondition or setup

        Product product1 = Product.builder()
                .id(2L)
                .company("ff")
                .type("aa")
                .grade("222")
                .markup(5)
                .costPrice(BigDecimal.valueOf(333))
                .finalPrice(BigDecimal.valueOf(313))
                .build();

        given(productRepository.findAll()).willReturn(List.of(product, product1));
        // when -  action or the behaviour that we are going test
        List<ProductDTO> productList = productService.getProducts();
        // then - verify the output
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);
    }

    // JUnit test for getAllProducts method
    @DisplayName("JUnit test for getAllProducts method (negative scenario)")
    @Test
    public void givenEmptyProductsList_whenGetAllProducts_thenReturnEmptyProductsList() {
        Product product1 = Product.builder()
                .id(2L)
                .company("ff")
                .type("aa")
                .grade("222")
                .markup(5)
                .costPrice(BigDecimal.valueOf(333))
                .finalPrice(BigDecimal.valueOf(313))
                .build();

        given(productRepository.findAll()).willReturn(Collections.emptyList());
        // when -  action or the behaviour that we are going test
        List<ProductDTO> productList = productService.getProducts();
        // then - verify the output
        assertThat(productList).isEmpty();
        assertThat(productList.size()).isEqualTo(0);
    }

    // JUnit test for updateProduct method
    @DisplayName("JUnit test for updateProduct method")
    @Test
    public void givenProductObject_whenUpdateProduct_thenReturnUpdatedProduct() {
        // given - precondition or setup
        Long id = 3L;
        given(productRepository.save(product)).willReturn(product);
        product.setId(id);
        product.setGrade("ram");
        product.setMarkup(13);
        // when -  action or the behaviour that we are going test
        Product updatedProduct = productService.updateProduct(id, ProductMapper.productToProductDTO(product));
        // then - verify the output
        assertThat(updatedProduct.getGrade()).isEqualTo("ram");
        assertThat(updatedProduct.getMarkup()).isEqualTo("13");
    }

    // JUnit test for deleteProduct method
    @DisplayName("JUnit test for deleteProduct method")
    @Test
    public void givenProductId_whenDeleteProduct_thenNothing() {
        // given - precondition or setup
        long productId = 1L;

        willDoNothing().given(productRepository).deleteById(productId);
        // when -  action or the behaviour that we are going test
        productService.deleteProduct(productId);
        // then - verify the output
        verify(productRepository, times(1)).deleteById(productId);
    }

    // JUnit test for getProductById method
    @DisplayName("JUnit test for getProductById method")
    @Test
    public void givenProductId_whenGetProductById_thenReturnProductObject() {

        Product product1 = Product.builder()
                .id(2L)
                .company("ff")
                .type("aa")
                .grade("222")
                .markup(5)
                .costPrice(BigDecimal.valueOf(333))
                .finalPrice(BigDecimal.valueOf(313))
                .build();
        Long productId = 2L;
        // given
        given(productRepository.findById(productId)).willReturn(Optional.of(product1));
        // when
        ProductDTO savedProduct = productService.getProductById(productId);
        // then
        assertThat(savedProduct).isNotNull();
    }
    
}
