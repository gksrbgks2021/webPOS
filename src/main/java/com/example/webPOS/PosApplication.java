package com.example.webPOS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PosApplication {

	public static void main(String[] args) {
		PosApplication posApplication = new PosApplication();
		posApplication.startApp();
	}
	public void startApp(){
		SpringApplication.run(PosApplication.class);
	}
}
