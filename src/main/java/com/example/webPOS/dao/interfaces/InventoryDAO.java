package com.example.webPOS.dao.interfaces;

import java.util.List;

import com.example.webPOS.dto.Inventory;

public interface InventoryDAO {
	List<Inventory> findAll(String storeName);

	int getQuantityByProductId(Long productID, String storeName);

	void update(Long productID, int quantity, String storeName, boolean IsOperationSum); // false (-) true (+)

	void insert(Long productID, int quantity, String storeName);
}
