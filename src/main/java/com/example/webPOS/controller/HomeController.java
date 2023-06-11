package com.example.webPOS.controller;

import com.example.webPOS.constants.ModelConstants;
import com.example.webPOS.constants.UrlParamAction;
import com.example.webPOS.dao.interfaces.InventoryDAO;
import com.example.webPOS.dao.interfaces.StoreInfoDAO;
import com.example.webPOS.dto.Member;
import com.example.webPOS.constants.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    private StoreInfoDAO storeInfoDAO;

    @Autowired
    public HomeController(StoreInfoDAO storeInfoDAO) {
        this.storeInfoDAO = storeInfoDAO;
    }

    @GetMapping("/home")
    public String showbutton(Model model,
                             @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member member){
        if(member != null){

            model.addAttribute("name",member.getName());
            model.addAttribute("role", member.getRole());

            return "home/main";
        }else{
            return "redirect:/login";
        }
    }
    @GetMapping("/trade")
    public String handeTrade(@RequestParam("selectedAction") String selectedAction,
                             Model model){
        model.addAttribute(ModelConstants.SELECTED_ACTION, selectedAction);
        if(selectedAction.equals(UrlParamAction.SALE)){
            model.addAttribute(ModelConstants.STORE_LIST, storeInfoDAO.findAll());
            return "home/trade/selectStore";
        }else if(selectedAction.equals(UrlParamAction.ORDER)){
            model.addAttribute(ModelConstants.STORE_LIST, storeInfoDAO.findAll());
            return "home/trade/selectStore";
        }

        return "redirect:/home";
    }

}
