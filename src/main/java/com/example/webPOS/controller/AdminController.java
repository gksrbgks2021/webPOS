package com.example.webPOS.controller;

import com.example.webPOS.dto.Product;
import com.example.webPOS.service.ProductMangeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {

    ProductMangeService productMangeService;

    @PostMapping("/admin/productEnroll")
    public String productEnrollment(
            @RequestParam("productID") String productID,
            @RequestParam("netprice") String netprice,
            @RequestParam("costprice") String costprice,
            @RequestParam("name") String name,
            @RequestParam("quantityreceived") int quantityreceived,
            Model model
    ){
        Product product = new Product(Long.parseLong(netprice),
                Long.parseLong(costprice),
                name);

        try {
            productMangeService.save(product);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "상품 등록에 실패하였습니다");
            return "home/admin/error";
        }

        model.addAttribute("infoMsg", "상품 등록에 성공하였습니다");
        return "home/admin/success";
    }

}


