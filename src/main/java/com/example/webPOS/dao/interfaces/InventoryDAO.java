package com.example.webPOS.dao.interfaces;

import java.util.List;

import com.example.webPOS.dto.Inventory;

public interface InventoryDAO {
	List<Inventory> findAll(String storeName);

	int getQuantityByProductId(String productID, String storeName);

	void update(String productID, int quantity, String storeName, boolean option); // false (-) true (+)

	void insert(String productID, int quantity, String storeName);
}
