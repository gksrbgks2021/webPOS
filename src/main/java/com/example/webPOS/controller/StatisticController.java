package com.example.webPOS.controller;


import com.example.webPOS.service.TradeStatisticService;
import jakarta.servlet.http.HttpServletRequest;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class StatisticController {

    TradeStatisticService tradeStatisticService;

    @Autowired
    public StatisticController(TradeStatisticService tradeStatisticService) {
        this.tradeStatisticService = tradeStatisticService;
    }

    @PostMapping("/statistic/query")
    public String showSelectedTrade(HttpServletRequest request, Model model) throws ParseException {

        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String interval = request.getParameter("interval");
        String store = request.getParameter("store");//편의점 이름

        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("interval", interval);
        model.addAttribute("store", store);
        //수익 정렬
        //Map<String, String> profitMap = tradeStatisticService.profitFirst(start, end, interval);
        //매출 정렬
        //Map<String, String> revenueMap = tradeStatisticService.revenueFirst(start, end, interval);

            List< Map.Entry<String, Integer> > rankingMap = tradeStatisticService.getRevenueRanking(start, end);
            List< Map.Entry<String, String>> rankByDay = tradeStatisticService.getTopStoreByDay(start, end);

            model.addAttribute("profitMap", rankingMap);
            model.addAttribute("revenueMap", rankByDay);

            List< Map.Entry<String, Integer>> profitmapList = tradeStatisticService.getProfitByPeriod(start, end, store,interval);
            model.addAttribute("storeProfitMap", profitmapList);

        return "home/statistic/queryOutput";
    }
}
