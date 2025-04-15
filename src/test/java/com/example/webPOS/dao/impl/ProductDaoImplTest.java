package com.example.webPOS.dao.impl;

import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@Transactional
class ProductDaoImplTest {

    @Autowired
    private ProductDAO productDAO;

    private Product product;

    @BeforeEach
    public void setup() {
        product = new Product();
        product.setNetPrice(100L);
        product.setCostPrice(50L);
        product.setName("Test Product");

    }

    @Test
    public void testSaveAndFindById() {
        // Save the product
        productDAO.save(product);
        // Fetch the product
        Product fetchedProduct = productDAO.findById(product.getProductId());

        // Check the product details
        assertNotNull(fetchedProduct);
        assertEquals(product.getName(), fetchedProduct.getName());
        assertEquals(product.getNetPrice(), fetchedProduct.getNetPrice());
        assertEquals(product.getCostPrice(), fetchedProduct.getCostPrice());
    }

    @Test
    public void testFindAll() {
        // Save the product
        productDAO.save(product);

        // Fetch all products
        List<Product> products = productDAO.findAll();

        // Check the products
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @AfterEach
    public void cleanup() {
        // Clean up the database after each test
    }
}
