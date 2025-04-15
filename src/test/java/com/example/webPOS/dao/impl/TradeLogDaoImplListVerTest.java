package com.example.webPOS.dao.impl;

import com.example.webPOS.constants.TradeState;
import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.vo.TradeLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class TradeLogDaoImplListVerTest {
    private static final Logger log = LoggerFactory.getLogger(TradeLogDaoImplListVerTest.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    TradeLogDAO tradeLogDAO;

    private Long productId;

    public Long getMaxProductId() {
        String sql = "SELECT MAX(productId) FROM tradelog";
        Integer maxProductId = jdbcTemplate.queryForObject(sql, Integer.class);

        // null 처리: 테이블이 비어 있을 경우 0 반환
        return (maxProductId != null) ?
                Long.valueOf(maxProductId)
                : 0L;
    }
    public Long getExistingProductId() {
        String sql = "SELECT productid FROM product LIMIT 1";
        Long existingProductId = jdbcTemplate.queryForObject(sql, Long.class);

        if (existingProductId == null) {
            throw new IllegalStateException("No existing product found in the 'product' table.");
        }
        log.info("📦 가져온 existingProductId = {}", existingProductId);

        return existingProductId;
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        if (jdbcTemplate == null) {
            throw new IllegalStateException("JdbcTemplate is null");
        }
    }

    @Test
    @Transactional
    void save() {
        //when
        productId = getExistingProductId();

        TradeLog tradeLog = new TradeLog();
        tradeLog.setProductId(productId);
        tradeLog.setTradeDate(LocalDateTime.now());
        tradeLog.setQuantityTraded(10);
        tradeLog.setTotalPrice(10000);
        tradeLog.setState(TradeState.SALED.getValue());
        tradeLog.setStoreName("Store A");

        TradeLog tradeLog1 = tradeLogDAO.save(tradeLog);

        //given
//        TradeLog savedTradeLog = tradeLogDAO.findById(tradeLog1.getId());

        //then
        assertNotNull(tradeLog1.getId());
        assertNotNull(tradeLog1);
        assertEquals(productId, tradeLog1.getProductId());
        assertEquals(tradeLog.getTradeDate(), tradeLog1.getTradeDate());
        assertEquals(tradeLog.getQuantityTraded(), tradeLog1.getQuantityTraded());
        assertEquals(tradeLog.getTotalPrice(), tradeLog1.getTotalPrice());
        assertEquals(tradeLog.getState(), tradeLog1.getState());
        assertEquals(tradeLog.getStoreName(), tradeLog1.getStoreName());
        assertEquals(tradeLog.getProductId(), tradeLog1.getProductId());

//        assertEquals(tradeLog.getProductId(), savedTradeLog.getProductId());
//        assertEquals(tradeLog.getTradeDate(), savedTradeLog.getTradeDate());
    }

    @Test
    void update() {
    }

    @Test
    @Transactional
    void findById() {

        //when
        productId = getExistingProductId();

        TradeLog tradeLog = new TradeLog();
        tradeLog.setProductId(productId);
        tradeLog.setTradeDate(LocalDateTime.now());
        tradeLog.setQuantityTraded(10);
        tradeLog.setTotalPrice(10000);
        tradeLog.setState(TradeState.SALED.getValue());
        tradeLog.setStoreName("Store A");

        TradeLog tradeLog1 = tradeLogDAO.save(tradeLog);
        //given
        TradeLog savedTradeLog = tradeLogDAO.findById(tradeLog1.getId());

        //then
        assertNotNull(savedTradeLog);
//        assertEquals(tradeLog.getProductId(), savedTradeLog.getProductId());
//        assertEquals(tradeLog.getTradeDate(), savedTradeLog.getTradeDate());

    }

    @Test
    void findAll() {
    }

}