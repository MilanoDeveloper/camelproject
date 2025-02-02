package com.camelrest.camelrest.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camelrest.camelrest.model.User;
import com.camelrest.camelrest.repository.UserRepository;

@Component
public class UserRoute extends RouteBuilder {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .component("servlet") // Define que usaremos o Servlet do Spring Boot
            .contextPath("/api")
            .bindingMode(RestBindingMode.json) // Configura JSON como formato de resposta
            .enableCORS(true); // Habilita CORS para evitar problemas

        // Define a API REST
        rest("/usuarios")
            .post().type(User.class).routeId("create-user").to("direct:createUser");

        from("direct:createUser")
            .process(exchange -> {
                User user = exchange.getMessage().getBody(User.class);
                userRepository.save(user);
                exchange.getMessage().setBody("Usuário cadastrado com sucesso!");
            });

        rest("/teste")
        .get()
        .to("direct:teste");

        from("direct:teste")
            .setBody(constant("Teste OK!"));
    }
}
