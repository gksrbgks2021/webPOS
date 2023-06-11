package com.example.webPOS.controller;

import com.example.webPOS.constants.ModelConstants;
import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dto.form.StoreNameForm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class SaleController {

    InventoryDAO inventoryDAO;

    @Autowired
    public SaleController(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    @GetMapping("/sale")
    public String handleSale1(HttpServletRequest httpServletRequest,
                              Model model){
        System.out.println(model.getAttribute(ModelConstants.SELECTED_ACTION));
        model.addAttribute(ModelConstants.STORE_NAME,
                httpServletRequest.getParameter(ModelConstants.STORE_NAME));

        return "home/trade/sale/step1";
    }
    @GetMapping("/sale/step2")
    public String handleSale2(HttpServletRequest httpServletRequest, Model model){

        return "home/trade/sale/step2";
    }

}
