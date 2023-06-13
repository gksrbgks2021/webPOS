package com.example.webPOS.controller;

import com.example.webPOS.constants.ModelConstants;
import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.service.OrderService;
import com.example.webPOS.service.StoreInfoService;
import com.example.webPOS.vo.TradeLog;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Controller
public class OrderController {

    OrderService orderService;
    ProductDAO productDAO;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/{storeName}")
    public String handleOrder1(@PathVariable("storeName") String storeName, Model model){
        model.addAttribute("storeName", storeName);
        model.addAttribute("productList", orderService.showProduct());
        return "/home/trade/order/step1";//주문할 상품 선택
    }
    @PostMapping("order/{storeName}/{totalPrice}/submit")
    public String ProcessAndCompleteOrder(@PathVariable("storeName") String storeName,
                                          @PathVariable("totalPrice") String totalPrice,
                                          HttpServletRequest request,
                                          Model model){

        model.addAttribute("totalPrice", totalPrice);
        Enumeration<String> parameterNames = request.getParameterNames();

        List<TradeLog> tradeLogList = new ArrayList<>(100){};

        int j=0;
        String[] paramList = new String[3];

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);

            //name, queantity, price  순으로 가져온다
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];

                paramList[j%3] = paramValue;
                j++;

                if(j % 3 == 0){
                    String productName = paramList[0];
                    int quantityTraded = Integer.parseInt(paramList[1]);
                    long productPrice = Long.parseLong(paramList[2]); // Assuming price parameter
                    System.out.println(Arrays.toString(paramList));

                    TradeLog tradeLog = new TradeLog();
                    tradeLog.setProductId(productDAO.findByName(productName).getProductId());
                    tradeLog.setTradeDate(LocalDateTime.now());
                    tradeLog.setQuantityTraded(quantityTraded);
                    tradeLog.setTotalPrice(productPrice);
                    tradeLog.setState("order");
                    tradeLog.setStoreName(storeName); // Assuming the store name is available

                    tradeLogList.add(tradeLog);
                }
//                if (paramName.startsWith("name")) {
//                    System.out.println("Name: " + paramValue);
//                } else if (paramName.startsWith("quantity")) {
//                    System.out.println("Quantity: " + paramValue);
//                } else if (paramName.startsWith("price")) {
//                    System.out.println("Price: " + paramValue);
//                }
            }
        }

        orderService.order(tradeLogList);

        System.out.println(" ====  [주문완료] ==== ");

        return "/home/trade/order/success";
    }
    @Autowired
    public void setProductDAO(ProductDAO productDAO){
        this.productDAO = productDAO;
    }
}
