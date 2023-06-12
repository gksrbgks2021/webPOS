package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.dto.Inventory;
import com.example.webPOS.dto.Product;
import com.example.webPOS.vo.TradeLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SaleService implements TradeService {
    TradeLogDAO tradeLogDAO;
    InventoryDAO inventoryDAO;
    ProductDAO productDAO;

    @Autowired
    public SaleService(TradeLogDAO tradeLogDAO, InventoryDAO inventoryDAO) {
        this.tradeLogDAO = tradeLogDAO;
        this.inventoryDAO = inventoryDAO;
    }
    @Override
    public List<Inventory> showInventory(String storeName) {
        return inventoryDAO.findAll(storeName);
    }

    @Override
    public List<Product> showProduct() {return productDAO.findAll(); }

    public void sale(List<TradeLog> tradeLog)throws Exception{
        StringBuilder sb = new StringBuilder();

        for (TradeLog trade : tradeLog) {
            //given
            String productName = productDAO.findById(trade.getProductId()).getName();
            int NumOfProduct = inventoryDAO.getQuantityByProductId(trade.getProductId(), trade.getStoreName());
            int NumOfrequest = trade.getQuantityTraded();

            if(NumOfProduct < NumOfrequest){//when
                sb.append(productName).append(" ").append(NumOfrequest - NumOfProduct).append("\n");
            }else{//then
                tradeLogDAO.save(trade);
            }
        }
        if(sb.length() > 0) throw new Exception(sb.toString());

    }

}
