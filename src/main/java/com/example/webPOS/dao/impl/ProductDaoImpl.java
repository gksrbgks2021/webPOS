package com.example.webPOS.dao.impl;

import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dto.Product;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class ProductDaoImpl implements ProductDAO {

    private final JdbcTemplate jdbcTemplate;
    String sqlQuery;

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }
    public String getSqlQuery(){
        return this.sqlQuery;
    }

    @Autowired
    public ProductDaoImpl(DataSource dataSource){this.jdbcTemplate =  new JdbcTemplate(dataSource);}

    @Override
    public List<Product> findAll() {
        setSqlQuery("SELECT * FROM PRODUCT");
        List<Product> results = jdbcTemplate.query( getSqlQuery() ,
                (ResultSet rs, int rowNum) -> {
                    Product product = new Product(
                            rs.getLong("netPrice"),
                    rs.getLong("costPrice"),
                            rs.getString("name"));
                    product.setProductId(rs.getLong("productId"));
                    return product;
                });
        return results;
    }

    @Override
    public void save(Product product) {
        setSqlQuery("INSERT INTO PRODUCT (netprice, costprice, name) VALUES (?, ?, ?)");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        getSqlQuery(),
                        new String[]{"ProductId"}); //생성된 id의 기본 키 값 반환
                pstmt.setLong(1, product.getNetPrice());
                pstmt.setLong(2, product.getCostPrice());
                pstmt.setString(3, product.getName());
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        product.setProductId(keyValue.longValue());//productId 삽입
    }

    @Override
    public Product findById(Long ProductID) {
        setSqlQuery("SELECT * FROM PRODUCT WHERER ProductID = ?");
        List<Product> results = jdbcTemplate.query(getSqlQuery(), new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product(
                        rs.getLong("netprice"),
                        rs.getLong("costprice"),
                        rs.getString("name"));
                product.setProductId(rs.getLong("productId"));
                return product;
            }
        }, ProductID);
        return results.isEmpty() ? null : results.get(0);
    }
}
