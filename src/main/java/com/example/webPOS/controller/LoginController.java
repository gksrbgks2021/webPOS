package com.example.webPOS.controller;

import com.example.webPOS.dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String login(){
        return "register/login";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request, MemberDTO memberDto, HttpSession session) throws Exception {
        System.out.println("login 메소드");
        MemberDTO result = userService.login(memberDto);
        if(result == null) {
            request.setAttribute("errorMsg", "회원 정보를 다시 입력해 주세요");
            throw new Exception();
        }
        session.setAttribute("email", result.getEmail());
        return "redirect:loginResult";
    }
}
