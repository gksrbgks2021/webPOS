package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.vo.TradeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class TradeStatisticService {

    public TradeLogDAO tradeLogDAO;

    @Autowired
    public TradeStatisticService(TradeLogDAO tradeLogDAO) {
        this.tradeLogDAO = tradeLogDAO;
    }

    public void save(TradeLog tradeLog) {
        tradeLogDAO.save(tradeLog);
    }

    public Map<String, Integer> getProfit(String start, String end, String StoreName, String period) {
        return tradeLogDAO.getProfit(start, end, StoreName, period);
    }

    public Map<String, String> profitFirst(String start, String end, String period) {
        return tradeLogDAO.profitFirst(start, end, period);
    }

    public Map<String, String> revenueFirst(String start, String end, String period) {
        return tradeLogDAO.revenueFirst(start, end, period);
    }


    public List<Map.Entry<String, Integer>> getProfitByPeriod(String start, String end, String storeName, String period) {
        return tradeLogDAO.getProfitByPeriod(start, end, storeName,period);
    }

    public List<Map.Entry<String, String>> getTopStoreByDay(String start, String end) {
        return tradeLogDAO.getTopStoreByDay(start, end);
    }

    public List<Map.Entry<String, Integer>> getRevenueRanking(String start, String end){
        return tradeLogDAO.getRevenueRanking(start, end);
    }

    }
