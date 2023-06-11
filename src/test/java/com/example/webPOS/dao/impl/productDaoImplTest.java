package com.example.webPOS.dao.impl;

import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class productDaoImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductDAO productDAO;

    @Test
    void findAll() {
        //List<Product>
    }

    @Test
    void save() {
    }
}