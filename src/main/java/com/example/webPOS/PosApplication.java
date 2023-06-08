package com.example.webPOS;

import com.example.webPOS.config.JavaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class PosApplication {
	AnnotationConfigApplicationContext ctx;

	public static void main(String[] args) {
		PosApplication posApplication = new PosApplication();
		posApplication.startApp();
	}
	public void startApp(){

		ctx = new AnnotationConfigApplicationContext();
		ctx.register(JavaConfig.class);

		SpringApplication.run(PosApplication.class);
		ctx.close();

	}

	public AnnotationConfigApplicationContext getCtx(){
		return this.ctx;
	}
}
