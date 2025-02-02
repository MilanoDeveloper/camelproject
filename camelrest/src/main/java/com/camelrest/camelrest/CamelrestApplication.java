package com.camelrest.camelrest;

import com.camelrest.camelrest.model.User;
import com.camelrest.camelrest.repository.UserRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.camelrest.camelrest")
public class CamelrestApplication{

	public static void main(String[] args) {
		SpringApplication.run(CamelrestApplication.class, args);
	}


}
