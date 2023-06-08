package com.example.webPOS.config;

import com.example.webPOS.controller.LoginController;
import com.example.webPOS.dao.MemberDAO;
import com.example.webPOS.dto.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.webPOS")
public class JavaConfig {

    @Bean
    public LoginController loginController(){
        return new LoginController();
    }

    @Bean
    public MemberDAO memberDAO(){
    }

}
