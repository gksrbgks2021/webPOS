package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.StoreInfoDAO;
import com.example.webPOS.dto.StoreInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreInfoServiceTest {

    @Autowired
    StoreInfoDAO storeInfoDAO;

    @Autowired
    StoreInfoService storeInfoService;

    @Test
    void findAllStore() {
        List<StoreInfo> l = storeInfoDAO.findAll();
        System.out.println(l.size());
    }

    @Test
    void 서비스테스트(){
        System.out.println(storeInfoService.findAllStore().size());
    }
}