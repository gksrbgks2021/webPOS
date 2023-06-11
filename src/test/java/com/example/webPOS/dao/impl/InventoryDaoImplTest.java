package com.example.webPOS.dao.impl;

import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dto.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InventoryDaoImplTest {

    @Autowired
    InventoryDAO inventoryDAO;

    @Test
    void findAll() {
        //given
        String n = "apple";

        //when
        List<Inventory> l = inventoryDAO.findAll(n);

        //then
        for(Inventory x : l)
            assertEquals(x.getStoreName(), n);
    }

    @Test
    void 상품판매테스트() {
        // given
        long productID = 100000; // productId는 이미 있는 거여야함.
        int initialQuantity = 10;
        int updatedQuantity = 5;
        String storeName = "MyStore";

        // insert initial inventory
        inventoryDAO.insert(productID, initialQuantity, storeName);

        // when
        inventoryDAO.update(productID, updatedQuantity, storeName, false); // decrease quantity
        List<Inventory> inventoryList = inventoryDAO.findAll(storeName);

        // then
        Assertions.assertEquals(1, inventoryList.size());
        Inventory inventory = inventoryList.get(0);
        Assertions.assertEquals(productID, inventory.getProductId());
        Assertions.assertEquals(initialQuantity - updatedQuantity, inventory.getQuantity());//10 - 5 = 5
        Assertions.assertEquals(storeName, inventory.getStoreName());

    }
    @Test
    void 상품주문테스트(){
        // given
        long productID = 100000; // productId는 이미 있는 거여야함.
        int initialQuantity = 10;
        int updatedQuantity = 5;
        String storeName = "MyStore";

        inventoryDAO.insert(productID, initialQuantity, storeName);

        // when
        inventoryDAO.update(productID, updatedQuantity, storeName, true); // decrease quantity
        List<Inventory> inventoryList = inventoryDAO.findAll(storeName);

        // then
        Assertions.assertEquals(1, inventoryList.size());
        Inventory inventory = inventoryList.get(0);
        Assertions.assertEquals(productID, inventory.getProductId());
        Assertions.assertEquals(initialQuantity + updatedQuantity, inventory.getQuantity());//10 - 5 = 5
        Assertions.assertEquals(storeName, inventory.getStoreName());

    }

    @Test
    void getQuantityByProductId() {
        // given
        long productID = 100000; // productId는 이미 있는 거여야함.
        int initialQuantity = 10;
        String storeName = "MyStore";

        inventoryDAO.insert(productID, initialQuantity, storeName);
        int ret = inventoryDAO.getQuantityByProductId(productID,storeName);

        assertEquals(initialQuantity, ret);
    }

    @Test
    void 관리자상품추가테스트() {
        // given
        long productID = 100000;
        int quantity = 10;
        String storeName = "MyStore";

        // when
        inventoryDAO.insert(productID, quantity, storeName);
        List<Inventory> inventoryList = inventoryDAO.findAll(storeName);

        // then
        assertEquals(1, inventoryList.size());
        Inventory inventory = inventoryList.get(0);
        assertEquals(productID, inventory.getProductId());
        assertEquals(quantity, inventory.getQuantity());
        assertEquals(storeName, inventory.getStoreName());
    }
}