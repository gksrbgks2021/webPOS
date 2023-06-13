package com.example.webPOS.dao.interfaces;

import com.example.webPOS.dto.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    void save(Product product);
    Product findById(Long ProductID);

    public int deleteById(Long pID);

    Product findByName(String name);
}
