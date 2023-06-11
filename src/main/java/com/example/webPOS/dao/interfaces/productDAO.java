package com.example.webPOS.dao.interfaces;

import com.example.webPOS.dto.Product;

import java.util.List;

public interface productDAO {
    List<Product> findAll();
    boolean save(Product product);
}
