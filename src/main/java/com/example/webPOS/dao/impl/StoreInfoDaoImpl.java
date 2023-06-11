package com.example.webPOS.dao.impl;


import com.example.webPOS.dao.interfaces.StoreInfoDAO;
import com.example.webPOS.dto.Inventory;
import com.example.webPOS.dto.StoreInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

public class StoreInfoDaoImpl implements StoreInfoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StoreInfoDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<StoreInfo> findAll() {
        List<StoreInfo> results = jdbcTemplate.query("select * from STOREINFO",
                (ResultSet rs, int rowNum) -> {
                    StoreInfo storeInfo = new StoreInfo(
                            rs.getString("storename"),
                            rs.getString("managername"));
                    return storeInfo;
                });
        return results;
    }
}
