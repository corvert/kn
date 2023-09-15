package com.example.kn.service;

import com.example.kn.model.Order;
import com.example.kn.model.Product;
import com.example.kn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product createProduct(Product product) {
        Product savedProduct = toProduct(product);
        return productRepository.save(savedProduct);
    }

    private Product toProduct(Product product) {
        return Product.builder()
                .productName(product.getProductName())
                .skuCode(product.getSkuCode())
                .unitPrice(product.getUnitPrice())
                .build();
    }


    public Product findProductId(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }


}
