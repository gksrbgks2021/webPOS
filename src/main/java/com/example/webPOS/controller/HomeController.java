package com.example.webPOS.controller;

import com.example.webPOS.dto.Member;
import com.example.webPOS.constants.SessionConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showbutton(Model model,
                             @SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member member){
        if(member != null){
            return "home/main";
        }else{
            return "redirect:/login";
        }
    }
}
