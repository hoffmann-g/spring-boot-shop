package com.example.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @SuppressWarnings("null")
    public ProductResponseDTO createProduct(ProductRequestDTO productDTO){
        Product product = productRepository.save(mapToProduct(productDTO));
        log.info("Product {} was created", product.getId());

        return mapToProductResponseDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(x -> mapToProductResponseDTO(x)).toList();
    }

    private ProductResponseDTO mapToProductResponseDTO(Product product){
        return ProductResponseDTO.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .build();
    }

    private Product mapToProduct(ProductRequestDTO productDTO){
        return Product.builder()
            .name(productDTO.name())
            .description(productDTO.description())
            .price(productDTO.price())
            .build();
    }

}
