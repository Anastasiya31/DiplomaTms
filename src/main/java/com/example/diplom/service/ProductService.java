package com.example.diplom.service;

import com.example.diplom.dto.ProductDTO;
import com.example.diplom.entity.Product;
import com.example.diplom.mapper.ProductMapper;
import com.example.diplom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    public Product createProduct(ProductDTO productDTO) {
        return productRepository.save(ProductMapper.productDtoToProduct(productDTO));
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .map(product -> {
                   ProductMapper.productDtoToProduct(productDTO);
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    productDTO.setId(id);
                    return productRepository.save( ProductMapper.productDtoToProduct(productDTO));
                });
    }

    public void deleteProduct(Long id) {
productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
//        Product product;
//        Optional<Product> productOptional = productRepository.findById(id);
//
//        if (productOptional.isPresent()) {
//            product = productOptional.get();
//        } else {
//            throw new EntityNotFountException("Product with id: " + id + " was not found");
//        }
//        return product;
        return productRepository.findById(id).orElse(null);
    }
}
