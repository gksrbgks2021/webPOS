package com.example.webPOS.dao.interfaces;

import com.example.webPOS.dto.TradeLog;

import java.util.List;

public interface TradeLogDAO {
    void save(TradeLog tradeLog);
    void update(TradeLog tradeLog);
    TradeLog findById(int id);
    List<TradeLog> findAll();
}
