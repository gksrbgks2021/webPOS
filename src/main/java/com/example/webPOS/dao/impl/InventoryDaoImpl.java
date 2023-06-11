package com.example.webPOS.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dto.Inventory;

public class InventoryDaoImpl implements InventoryDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public InventoryDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Inventory> findAll(String storeName) {
		List<Inventory> results = jdbcTemplate.query("select * from INVENTORY WHERE storename = ?", (ResultSet rs, int rowNum) -> {
			Inventory inventory = new Inventory(rs.getString("PRODUCT_ID"), rs.getInt("quantity"), rs.getString("storename"));
			return inventory;
		}, storeName);
		return results;
	}

}
