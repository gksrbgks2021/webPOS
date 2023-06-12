package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.dto.Inventory;
import com.example.webPOS.dto.Product;
import com.example.webPOS.vo.TradeLog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Transactional
class OrderServiceTest {


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

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOrder2() {
        // given
        TradeLog tradeLog = new TradeLog();
        tradeLog.setId(1);
        tradeLog.setProductId(1000000L);
        tradeLog.setTradeDate(LocalDateTime.now());
        tradeLog.setQuantityTraded(10);
        tradeLog.setTotalPrice(1000);
        tradeLog.setState("completed");
        tradeLog.setStoreName("Store1");

        List<TradeLog> tradeLogList = Arrays.asList(tradeLog);
        // stubbing
        when(inventoryDAO.getQuantityByProductId(anyLong(), anyString())).thenReturn(1);
        // when
        orderService.order(tradeLogList);
        // then
        int beforeVal = inventoryDAO.getQuantityByProductId(tradeLog.getProductId(), tradeLog.getStoreName());
        //update호출되었는지
        verify(tradeLogDAO, times(1)).save(tradeLog);
        verify(inventoryDAO, times(1)).update(
                eq(tradeLog.getProductId()),
                eq(tradeLog.getQuantityTraded()),
                eq(tradeLog.getStoreName()),
                eq(true)
        );
        // 데이터베이스에서 값을 확인하고 assertEquals를 사용하여 검증

        assertEquals(tradeLog.getQuantityTraded(), inventoryDAO.getQuantityByProductId(tradeLog.getProductId(), tradeLog.getStoreName()));
        assertNotEquals(beforeVal, inventoryDAO.getQuantityByProductId(tradeLog.getProductId(), tradeLog.getStoreName()));

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