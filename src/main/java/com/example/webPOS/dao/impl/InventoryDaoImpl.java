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

	String sqlQeury;

	public String getSqlQeury() {
		return sqlQeury;
	}

	public void setSqlQeury(String sqlQeury) {
		this.sqlQeury = sqlQeury;
	}

	@Autowired
	public InventoryDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Inventory> findAll(String storeName) {
		setSqlQeury("select * from INVENTORY WHERE storename = ?");
		List<Inventory> results = jdbcTemplate.query(getSqlQeury(),
				(ResultSet rs, int rowNum) -> {
					Inventory inventory = new Inventory(
							rs.getLong("productid"),
							rs.getInt("quantity"),
							rs.getString("storename"));
					return inventory;
				}, storeName);
		return results;
	}
	@Override
	public int getQuantityByProductId(Long productID, String storeName) {
		System.out.println(productID + " " + storeName);
		setSqlQeury("SELECT quantity FROM inventory WHERE productid = ? AND storename = ?");
		Integer res=  jdbcTemplate.queryForObject(getSqlQeury(),
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return resultSet.getInt("quantity");
					}
				}, productID, storeName);
		return res != null ? res.intValue() : 0;
	}

	@Override
	public void update(Long productID, int quantity, String storeName, boolean OperationIsPlus) { // false (-) true (+)
		if (OperationIsPlus) { // (+)

			setSqlQeury("UPDATE inventory SET " + "quantity = ? " + "WHERE productid = ?");

			jdbcTemplate.update(getSqlQeury(),
					getQuantityByProductId(productID, storeName) + quantity
					, productID);
		} else { // (-)

			setSqlQeury("UPDATE inventory SET " + "quantity = ? " + "WHERE productid = ?");

			jdbcTemplate.update(getSqlQeury(),
					getQuantityByProductId(productID, storeName) - quantity
					, productID);
		}
	}

	@Override
	public boolean existProduct(Long productID, String storeName) {
		System.out.println(productID + " " + storeName);
		setSqlQeury("SELECT exists ( select quantity FROM inventory WHERE productid = ? AND storename = ?)");
		Boolean res =  jdbcTemplate.queryForObject(getSqlQeury(),
				Boolean.class, productID, storeName);
		return res;
	}

	@Override
	//관리자 상품 추가.
	public void insert(Long productID, int quantity, String storeName) {
		setSqlQeury("INSERT INTO inventory (productid, quantity, storename) VALUES (?, ?, ?)");
		jdbcTemplate.update(getSqlQeury(),
				productID,
				quantity,
				storeName);
	}
}
