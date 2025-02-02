package com.camelrest.camelrest.routes;

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
                    .component("servlet")
                    .contextPath("/api")
                    .bindingMode(RestBindingMode.json)
                    .dataFormatProperty("prettyPrint", "true")
                    .enableCORS(true);

            rest("/usuarios")
                    .post().type(User.class).outType(String.class).routeId("create-user").to("direct:createUser");

            from("direct:createUser")
                    .log("Mensagem recebida no formato JSON: ${body}")
                    .process(exchange -> {
                        String body = exchange.getIn().getBody(String.class);
                        log.info("Corpo da mensagem antes da conversão: " + body);
                    })
                    .process(exchange -> {
                        try {
                            User user = exchange.getIn().getBody(User.class);
                            userRepository.save(user);
                            exchange.getMessage().setBody("Usuário cadastrado com sucesso!");
                        } catch (Exception e) {
                            log.error("Erro ao converter o corpo da mensagem para User: ", e);
                            throw e;
                        }
                    })
                    .log("Mensagem processada e salva no banco de dados.");

        rest("/teste")
        .get()
        .to("direct:teste");

        from("direct:teste")
            .setBody(constant("Teste OK!"));
    }
}
