package com.camelrest.camelrest.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;

@Configuration
public class CamelConfig {

    @Bean
    public ServletRegistrationBean<CamelHttpTransportServlet> camelServlet() {
        ServletRegistrationBean<CamelHttpTransportServlet> servlet = new ServletRegistrationBean<>(new CamelHttpTransportServlet(), "/api/*");
        servlet.setName("CamelServlet");
        return servlet;
    }
}
