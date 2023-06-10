package com.example.webPOS.dao.impl;

import com.example.webPOS.dao.interfaces.productDAO;
import com.example.webPOS.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class productDaoImpl implements productDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    productDaoImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean save(Product product) {
        int rowsAffected = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "INSERT INTO PRODUCT (productid, netprice, costprice, name) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, product.getProductId());
                pstmt.setLong(2, product.getNetPrice());
                pstmt.setLong(3, product.getCostPrice());
                pstmt.setString(4, product.getName());
                return pstmt;
            }
        });
        return rowsAffected > 0;//업데이트 성공하면, true 리턴.
    }
}
