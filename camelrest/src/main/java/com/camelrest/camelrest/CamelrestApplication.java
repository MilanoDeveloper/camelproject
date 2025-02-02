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
public class CamelrestApplication extends RouteBuilder {

	public static void main(String[] args) {
		SpringApplication.run(CamelrestApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Bean
    public CamelContext camelContext(ApplicationContext applicationContext)  { // Este método
        return new SpringCamelContext(applicationContext);
    }

	@Override
	public void configure() throws Exception {
		restConfiguration()
				.component("servlet") // Define que usaremos o Servlet do Spring Boot
				.contextPath("/api")
				.bindingMode(RestBindingMode.json) // Configura JSON como formato de resposta
				.enableCORS(true); // Habilita CORS para evitar problemas

		rest("/usuarios")
				.post().type(User.class).routeId("create-user").to("direct:createUser");

		from("direct:createUser")
				.process(exchange -> {
					User user = exchange.getMessage().getBody(User.class);
					userRepository.save(user); // Usa o repositório injetado
					exchange.getMessage().setBody("Usuário cadastrado com sucesso!");
				});
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
