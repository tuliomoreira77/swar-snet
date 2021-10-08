package br.com.swar.snet.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig  {
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

	
	private Info apiInfo() {
		return new Info()
            .title ("Swar-Net - Stars Wars Resistence Social Network")
            .description ("O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e eliminar os rebeldes. "
            		+ "Esse é um sistema para compartilhar recursos entre os rebeldes.")
            .version("1.0.0")
            .contact(new Contact().email("tuliomoreira77@gmail.com").name("Marco Tulio Moreira"));
    }
	
}