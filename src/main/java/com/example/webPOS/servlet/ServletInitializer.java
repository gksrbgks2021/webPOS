package com.example.webPOS.servlet;

import com.example.webPOS.PosApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//외부로 war 파일로 배포할때 사용됨
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PosApplication.class);
    }
}
