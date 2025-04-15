package com.example.webPOS.dao.impl;

import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.vo.TradeLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class TradeLogDaoImplListVerTest {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    TradeLogDAO tradeLogDAO;

    @Test
    @Transactional
    void save() {
        TradeLog tradeLog = new TradeLog();
        tradeLog.setProductId(1232200L);
        tradeLog.setTradeDate(LocalDateTime.now());
        tradeLog.setQuantityTraded(10);
        tradeLog.setTotalPrice(10000);
        tradeLog.setState("COMPLETE");
        tradeLog.setStoreName("Store A");

        tradeLogDAO.save(tradeLog);

        // 적절한 검증 작업 수행
        TradeLog savedTradeLog = tradeLogDAO.findById((long)tradeLog.getId());
        assertEquals(tradeLog.getProductId(), savedTradeLog.getProductId());
        assertEquals(tradeLog.getTradeDate(), savedTradeLog.getTradeDate());
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

}