package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.dto.Inventory;
import com.example.webPOS.vo.TradeLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService implements TradeService {
    TradeLogDAO tradeLogDAO;
    InventoryDAO inventoryDAO;
    ProductDAO productDAO;

    public TradeLogDAO getTradeLogDAO() {
        return tradeLogDAO;
    }


    @Autowired
    public void setTradeLogDAO(TradeLogDAO tradeLogDAO) {
        this.tradeLogDAO = tradeLogDAO;
    }

    public InventoryDAO getInventoryDAO() {
        return inventoryDAO;
    }

    @Autowired
    public void setInventoryDAO(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public OrderService(TradeLogDAO tradeLogDAO, InventoryDAO inventoryDAO, ProductDAO productDAO) {
        this.tradeLogDAO = tradeLogDAO;
        this.inventoryDAO = inventoryDAO;
        this.productDAO = productDAO;
    }

    @Override
    public List<Inventory> showInventory(String storeName) {
        return inventoryDAO.findAll(storeName);
    }

    public void order(List<TradeLog> tradeLog){
        for (TradeLog trade : tradeLog) {
            String productName = productDAO.findById(trade.getProductId()).getName();

        }
    }
}
