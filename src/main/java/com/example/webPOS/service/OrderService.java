package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.dto.Inventory;
import com.example.webPOS.dto.Product;
import com.example.webPOS.vo.TradeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public List<Product> showProduct() {  return productDAO.findAll();}

    public void order(List<TradeLog> tradeLog) {
        for (TradeLog trade : tradeLog) {
            //given
            String productName = productDAO.findById(trade.getProductId()).getName();
            int NumOfProduct = inventoryDAO.getQuantityByProductId(trade.getProductId(), trade.getStoreName());
            int NumOfrequest = trade.getQuantityTraded();

            tradeLogDAO.save(trade);
            if (NumOfProduct == 0) {
                inventoryDAO.insert(trade.getProductId(), NumOfrequest, trade.getStoreName());
            } else {
                inventoryDAO.update(trade.getProductId(), NumOfrequest, trade.getStoreName(), true);
            }
        }
    }
}
