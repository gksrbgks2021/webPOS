package com.example.webPOS.dao.interfaces;


import com.example.webPOS.dto.StoreInfo;

import java.util.List;

public interface StoreInfoDAO {
    List<StoreInfo> findAll();

    List<StoreInfo> findByEmail(String email);
}
