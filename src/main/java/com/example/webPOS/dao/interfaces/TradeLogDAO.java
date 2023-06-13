package com.example.webPOS.dao.interfaces;

import com.example.webPOS.vo.TradeLog;

import java.util.List;
import java.util.Map;

public interface TradeLogDAO {
    void save(TradeLog tradeLog);
    Map<String, Integer> getProfit(String start, String end, String StoreName, String period);
    Map<String, String> profitFirst(String start, String end, String period);
    Map<String, String> revenueFirst(String start, String end, String period);
    TradeLog findById(Long productID);

    public List<Map.Entry<String, Integer>> getProfitByPeriod(String start, String end, String storeName, String period);

        //temp
    public List<Map.Entry<String, String>> getTopStoreByDay(String start, String end);

    public List<Map.Entry<String, Integer>> getRevenueRanking(String start, String end) ;

    }
