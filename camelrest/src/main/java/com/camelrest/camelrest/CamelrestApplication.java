package com.camelrest.camelrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.camelrest.camelrest")
public class CamelrestApplication{

	public static void main(String[] args) {
		SpringApplication.run(CamelrestApplication.class, args);
	}
}
