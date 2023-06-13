package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Product> findAll(){
        return productDAO.findAll();
    }
    public int deleteByProductId(Long pid){
        return productDAO.deleteById(pid);
    }

}
