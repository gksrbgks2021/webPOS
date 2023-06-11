package com.example.webPOS.controller;

import com.example.webPOS.service.StoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    StoreInfoService storeInfoService;

    @Autowired
    StoreController(StoreInfoService storeInfoService){
        this.storeInfoService = storeInfoService;
    }

    @GetMapping("/store")
    public String selectStore(Model model){
        model.addAttribute("storeInfo", storeInfoService.findAllStore());
        return "home/store/selectStore";
    }

}
