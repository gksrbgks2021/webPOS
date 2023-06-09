package com.example.webPOS.controller;

import com.example.webPOS.dto.RegisterDTO;
import com.example.webPOS.service.MemberRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    MemberRegisterService memberRegisterService;

    @Autowired
    RegisterController(MemberRegisterService memberRegisterService){
        this.memberRegisterService = memberRegisterService;
    }

    @PostMapping("/register/step1")
    public String handleStep1() { return "register/step1"; }

    @PostMapping("/register/step2")
    public String handleStep2(@RequestParam(value = "agree",defaultValue = "false")
                              Boolean agree, Model model) {

        System.out.println("postmapping register/step2 들어옴.");

        if (!agree) {//약관 동의 안했으면
            System.out.println("동의안함");
            return "register/step1";
        }

        return "register/step2";
    }

    @GetMapping("/register/step2")
    public String handleStep2Get() {
        System.out.println("@GetMapping('/register/step2') 들어옴");
        return "redirect:/register/step1"; }

    @PostMapping("/register/step3")
    public String handleStep3(RegisterDTO regReq) {
        try {
            memberRegisterService.regist(regReq);
            return "register/step3";
        } catch (Exception ex) {
            return "register/step2";
        }
    }

    @Autowired
    public void setMemberRegisterService(MemberRegisterService memberRegisterService){
        this.memberRegisterService = memberRegisterService;
    }
}