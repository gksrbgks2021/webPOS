package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.StoreInfoDAO;
import com.example.webPOS.dto.StoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreInfoService {

    private StoreInfoDAO storeInfoDAO;

    @Autowired
    StoreInfoService(StoreInfoDAO storeInfoDAO){
        this.storeInfoDAO = storeInfoDAO;
    }


    public List<StoreInfo> findAllStore(){
        return storeInfoDAO.findAll();
    }

}
