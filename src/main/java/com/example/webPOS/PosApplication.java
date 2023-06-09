package com.example.webPOS;

import com.example.webPOS.config.JavaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class PosApplication {
	public static void main(String[] args) {
		PosApplication posApplication = new PosApplication();
		posApplication.startApp();
	}
	public void startApp(){
		SpringApplication.run(PosApplication.class);
	}
}
