package com.example.webPOS.controller;

import com.example.webPOS.constants.ModelConstants;
import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dao.interfaces.ProductDAO;
import com.example.webPOS.dto.form.StoreNameForm;
import com.example.webPOS.service.OrderService;
import com.example.webPOS.service.SaleService;
import com.example.webPOS.vo.TradeLog;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Controller
public class SaleController {

    /**
     * 필드 정의 부분
     */

    ProductDAO productDAO;

    SaleService saleService;
    OrderService orderService;
    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public SaleService getSaleService() {
        return saleService;
    }

    @Autowired
    public void setSaleService(SaleService saleService) {
        this.saleService = saleService;
    }



    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *  메소드 부분
     */
    @GetMapping("/sale/{storeName}")
    public String handleSale1(@PathVariable("storeName") String storeName, Model model){
        model.addAttribute("storeName", storeName);
        model.addAttribute("productList", orderService.showProduct());
        return "/home/trade/sale/step1";//판매할 상품 선택
    }
    @PostMapping("/sale/{storeName}/{totalPrice}/submit")
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
                    tradeLog.setState("saled");
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

        String msg="";
        try{
            saleService.sale(tradeLogList);
            return "/home/trade/sale/success";
        }catch (Exception e){
            msg = e.getMessage();
            model.addAttribute("errorMsg", msg);
            return "/home/trade/sale/SaleErrorPage";
        }
    }


}
