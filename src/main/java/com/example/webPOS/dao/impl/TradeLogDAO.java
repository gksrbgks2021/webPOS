package com.example.webPOS.dao.interfaces;

import com.example.webPOS.vo.TradeLog;

import java.util.List;
import java.util.Map;

public interface TradeLogDAO {
    void save(TradeLog tradeLog);
    Map<String, Integer> getProfit(String start, String end, String StoreName, String period);
    Map<String, String> profitFirst(String start, String end, String period);
    Map<String, String> takeFirst(String start, String end, String period);

    TradeLog findById(Long productID);
}
