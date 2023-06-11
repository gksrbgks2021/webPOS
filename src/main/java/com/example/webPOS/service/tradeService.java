package com.example.webPOS.service;

import com.example.webPOS.dao.impl.InventoryDaoImpl;
import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dto.Inventory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface tradeService {

    List<Inventory> showInventory();

}
