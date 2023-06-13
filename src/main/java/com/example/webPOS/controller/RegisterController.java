package com.example.webPOS.controller;

import com.example.webPOS.dto.form.RegisterForm;
import com.example.webPOS.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    MemberService memberService;

    @Autowired
    RegisterController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("register/")
    public String handleStep1() {
        System.out.println("handlestep1");
        return "register/step1"; }

    @PostMapping("/register/step2")
    public String handleStep2(@RequestParam(value = "agree",defaultValue = "false")
                              Boolean agree, Model model) {

        if (!agree) {//약관 동의 안했으면
            System.out.println("동의안함");
            return "register/step1";
        }
        /**
         * make form instance and add to model
         */
        model.addAttribute("registerform", new RegisterForm());

        return "register/step2";
    }

    @GetMapping("/register/step2")
    public String handleStep2Get() {
        return "redirect:/register/step1";
    }

    @PostMapping("/register/step3")
    public String handleStep3(HttpServletRequest request, Model model) {
        String e = request.getParameter("email");
        String n = request.getParameter("name");
        String p = request.getParameter("password");
        String cp = request.getParameter("confirmPassword");
        String r = request.getParameter("role");

        RegisterForm reg = new RegisterForm(e,p,cp,n,r);

        try {
            memberService.regist(reg);
            model.addAttribute("name",n);
            return "register/step3";
        } catch (Exception ex) {//이메일 중복.
            return "register/step2";
        }
    }

}