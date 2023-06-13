package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMangeService {
    ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void save(Product product)throws Exception{
        productDAO.save(product);
    }
}
