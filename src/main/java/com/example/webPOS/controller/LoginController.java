package com.example.webPOS.controller;

import com.example.webPOS.dto.Member;
import com.example.webPOS.dto.form.LoginForm;
import com.example.webPOS.service.LoginService;
import com.example.webPOS.constants.SessionConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    LoginController(LoginService loginService){this.loginService = loginService;}

    @RequestMapping("/")
    public String login(){
        return "login/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, LoginForm loginForm) throws Exception {

        String id = request.getParameter("id");
        String pw = request.getParameter("password");


        Member loginMember = loginService.login(id,pw);

        if(loginMember == null) {
            System.out.println("아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/login";
        }

        //로그인 성공 처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.LOGIN_MEMBER,loginMember);//session 에 멤버 객체 추가.

        return "redirect:home";
    }

    @GetMapping("/logout")      //세션날림
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionConstants.LOGIN_MEMBER);

        return "redirect:/";
    }
}
