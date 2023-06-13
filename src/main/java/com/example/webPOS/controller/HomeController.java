package com.example.webPOS.controller;

import com.example.webPOS.constants.ModelConstants;
import com.example.webPOS.constants.urlpath.UrlParamAction;
import com.example.webPOS.dao.interfaces.StoreInfoDAO;
import com.example.webPOS.dto.Member;
import com.example.webPOS.constants.SessionConstants;
import com.example.webPOS.service.StoreInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public StoreInfoService storeInfoService;

    @Autowired
    public HomeController(StoreInfoService storeInfoService) {
        this.storeInfoService = storeInfoService;
    }

    @GetMapping("/home")
    public String showbutton(Model model,
                             @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member member) {
        if (member != null) {

            model.addAttribute("name", member.getName());
            model.addAttribute("role", member.getRole());
            logger.info("Redirecting to home/main");

            return "home/main";
        } else {
            logger.info("Redirecting to /login");
            return "redirect:/login";
        }
    }

    @GetMapping("/trade/actionName/{action}")
    public String handeTrade(@PathVariable("action") String action,
                             @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member member,
                             HttpServletRequest request,
                             Model model) {

        String attributeName = "actionName";
        StringBuilder sb = new StringBuilder();

        model.addAttribute(attributeName, action);

        if (action.equals(UrlParamAction.SALE)) {

            sb.append("home/trade/selectStore");
            model.addAttribute("storeList", storeInfoService.findAllStore());
            return sb.toString();

        } else if (action.equals(UrlParamAction.ORDER)) {

            sb.append("home/trade/selectStore");
            model.addAttribute("storeList", storeInfoService.findByManagerName(member.getEmail()));
            return sb.toString();

        }
        return "redirect:/home";
    }

    //selectStore 페이지를 받아옴
    @PostMapping("/trade/actionName/{action}/storeName/{storeName}")
    public String handleOrder(@PathVariable("action") String action,
                              @PathVariable("storeName") String storeName,
                              Model model) {

        StringBuilder sb = new StringBuilder();
        if (action.equals("order")) {
            sb.append("redirect:").append("/order/").append(storeName);
        } else if (action.equals("sale")) {
            sb.append("redirect:").append("/sale/").append(storeName);
        }

        return sb.toString();
    }

    //작업이 끝나고 홈으로
    @GetMapping("/finish")
    public String returnHome(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member member,
                             Model model){
        if(member == null){
            System.out.println("세션 없어짐");
            return "redirect:/login";
        }
        model.addAttribute("name", member.getName());
        model.addAttribute("role", member.getRole());

        return "redirect:home/main";
    }

    @GetMapping("/statistic")
    public String handleStatistic(Model model){
        model.addAttribute("storeList", storeInfoService.findAllStore());

        return "home/statistic/selectQuery";
    }
}