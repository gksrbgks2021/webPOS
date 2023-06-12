package com.example.webPOS.controller;

import com.example.webPOS.constants.ModelConstants;
import com.example.webPOS.service.OrderService;
import com.example.webPOS.service.StoreInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/{storeName}")
    public String handleOrder1(@PathVariable("storeName") String storeName, Model model){
        model.addAttribute("productList", orderService.showProduct());
        return "/home/trade/order/step1";//주문할 상품 선택
    }
    @PostMapping("order/submit")
    public String ProcessAndCompleteOrder(HttpServletRequest request){

        return "/home/trade/order/success";
    }
}
