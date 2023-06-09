package com.example.webPOS.controller;

import com.example.webPOS.dto.MemberDTO;
import com.example.webPOS.dto.RegisterDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @RequestMapping("/")
    public String login(){
        System.out.println("로그인 페이지 이동");
        return "register/login";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request, HttpSession session) throws Exception {
        System.out.println("login 메소드");
        //코드수정
        MemberDTO result = null;
        if(result == null) {
            request.setAttribute("errorMsg", "회원 정보를 다시 입력해 주세요");
            throw new Exception();
        }

        session.setAttribute("email", result.getEmail());
        return "redirect:loginResult";
    }
}
