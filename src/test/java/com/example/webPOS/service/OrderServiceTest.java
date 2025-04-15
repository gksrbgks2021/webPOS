package com.example.webPOS.service;

import com.example.webPOS.config.JavaConfig;
import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.dto.Inventory;
import com.example.webPOS.dto.Product;
import com.example.webPOS.vo.TradeLog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
//@ContextConfiguration(classes = {JavaConfig.class})
class OrderServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private OrderService orderService;

    /**
     * 단위 테스트 위한 Mock인스턴스 생성.
     */
    @Mock
    private TradeLogDAO tradeLogDAO;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private InventoryDAO inventoryDAO;


    private Long productId;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        if (jdbcTemplate == null) {
            throw new IllegalStateException("JdbcTemplate is null");
        }
        productId = getMaxProductId() + 1L;
    }

    @Transactional
    @Test
    public void testOrder2() {
        // given
        String storeName = "Store1";
        TradeLog tradeLog = new TradeLog();
        tradeLog.setId(1);
        tradeLog.setProductId(productId);
        tradeLog.setTradeDate(LocalDateTime.now());
        tradeLog.setQuantityTraded(10);
        tradeLog.setTotalPrice(1000);
        tradeLog.setState("completed");
        tradeLog.setStoreName(storeName);

        List<TradeLog> tradeLogList = Arrays.asList(tradeLog);
        // Mocking
        when(inventoryDAO.existProduct(productId, storeName)).thenReturn(true);
        when(inventoryDAO.getQuantityByProductId(anyLong(),anyString()))
                .thenReturn(1)
                .thenReturn(11);

        // when
        int beforeVal = inventoryDAO.getQuantityByProductId( tradeLog.getProductId(), tradeLog.getStoreName());
        orderService.order(tradeLogList);

        // then: update호출되었는지
        verify(tradeLogDAO, times(1)).save(tradeLog);
        verify(inventoryDAO, times(1)).update(
                eq(tradeLog.getProductId()),
                eq(tradeLog.getQuantityTraded()),
                eq(tradeLog.getStoreName()),
                eq(true)
        );
        // 데이터베이스에서 값을 확인하고 assertEquals를 사용하여 검증
        assertEquals(beforeVal + tradeLog.getQuantityTraded(),
                inventoryDAO.getQuantityByProductId(tradeLog.getProductId(), tradeLog.getStoreName()));
        assertNotEquals(beforeVal,
                inventoryDAO.getQuantityByProductId(tradeLog.getProductId(), tradeLog.getStoreName()));

    }
    public Long getMaxProductId() {
        String sql = "SELECT MAX(productId) FROM tradelog";
        Integer maxProductId = jdbcTemplate.queryForObject(sql, Integer.class);

        // null 처리: 테이블이 비어 있을 경우 0 반환
        return (maxProductId != null) ?
                Long.valueOf(maxProductId)
                : 0L;
    }

    @Test
    public void testOrder() {
        // given
        TradeLog tradeLog = new TradeLog();
        tradeLog.setId(1);
        tradeLog.setProductId(1L);
        tradeLog.setTradeDate(LocalDateTime.now());
        tradeLog.setQuantityTraded(10);
        tradeLog.setTotalPrice(1000);
        tradeLog.setState("completed");
        tradeLog.setStoreName("Store1");

        List<TradeLog> tradeLogList = Arrays.asList(tradeLog);

        //stubbing
        when(productDAO.findById(anyLong())).thenReturn(new Product());
        when(inventoryDAO.getQuantityByProductId(anyLong(), anyString())).thenReturn(0);

        // when
        orderService.order(tradeLogList);

        // then
        //검증
        //time : 1 은 해당 메서드가 1번 호출되었는지 의미
        verify(tradeLogDAO, times(1)).save(tradeLog);
        verify(inventoryDAO, times(1)).insert(anyLong(), anyInt(), anyString());

    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
    }
}