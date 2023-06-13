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
public class SaleService implements TradeService {
    TradeLogDAO tradeLogDAO;
    InventoryDAO inventoryDAO;
    ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

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

            //상품이 유효하면 조회 쿼리 실행.
            if (inventoryDAO.existProduct(trade.getProductId(), trade.getStoreName())){
                int NumOfProduct = inventoryDAO.getQuantityByProductId(trade.getProductId(), trade.getStoreName());
                int NumOfrequest = trade.getQuantityTraded();

                if(NumOfProduct < NumOfrequest){//when
                    sb.append(productName).
                            append("상품이 ").
                            append(NumOfrequest - NumOfProduct).append(" 개 부족합니다.<br/>");
                }else{//then
                    tradeLogDAO.save(trade);
                }
            }else{
                sb.append(productName).
                        append("상품이 ").
                        append("없습니다.<br/>");

            }

        }
        if(sb.length() > 0) {
            System.out.println("에러 메시지 출력");
            throw new Exception(sb.toString());
        }
    }

}
