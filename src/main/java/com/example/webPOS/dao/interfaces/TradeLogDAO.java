package com.example.webPOS.dao.interfaces;

import com.example.webPOS.vo.TradeLog;

import java.util.List;

public interface TradeLogDAO {
    void save(TradeLog tradeLog);
    List<TradeLog> findPeriod(String start, String end, String StoreName);
    List<TradeLog> findPeriodDesc(String start, String end, String dateType);//기간 조회

    TradeLog findById(Long productID);
}
