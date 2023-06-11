package com.example.webPOS.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
		List<Inventory> results = jdbcTemplate.query("select * from INVENTORY WHERE storename = ?",
				(ResultSet rs, int rowNum) -> {
					Inventory inventory = new Inventory(rs.getString("productid"), rs.getInt("quantity"),
							rs.getString("storename"));
					return inventory;
				}, storeName);
		return results;
	}

	public int getQuantityByProductId(String productID, String storeName) {
		return jdbcTemplate.queryForObject("SELECT quantity FROM inventory WHERE productid = ?" + " AND storename = ?",
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return resultSet.getInt("quantity");
					}
				}, productID, storeName);

	}

	public void update(String productID, int quantity, String storeName, boolean option) { // false (-) true (+)
		if (option) { // (+)
			jdbcTemplate.update("UPDATE inventory SET " + "quantity = ? " + "WHERE productid = ?",
					getQuantityByProductId(productID, storeName) + quantity, productID);
		} else { // (-)
			jdbcTemplate.update("UPDATE inventory SET " + "quantity = ? " + "WHERE productid = ?",
					getQuantityByProductId(productID, storeName) - quantity, productID);
		}
	}

	public void insert(String productID, int quantity, String storeName) {
		String sql = "INSERT INTO inventory (productid, quantity, storename) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, productID, quantity, storeName);
	}
}
