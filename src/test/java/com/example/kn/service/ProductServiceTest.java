package com.example.kn.service;

import com.example.kn.model.Product;
import com.example.kn.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @Test
    public void testCreateProduct() {

        Product product = new Product();
        product.setProductName("Test Product");
        product.setSkuCode("SKU123");
        product.setUnitPrice(10.0);

        when(productRepository.save(any())).thenReturn(product);

        Product savedProduct = productService.createProduct(product);

        assertNotNull(savedProduct);
        assertEquals("Test Product", savedProduct.getProductName());

    }

    @Test
    public void testFindProductId() {

        long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findProductId(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getId().longValue());
    }

}
