package com.aligntechnology.sampletask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.aligntechnology.sampletask"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class SpringBootMainApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMainApp.class, args);
	}
}
