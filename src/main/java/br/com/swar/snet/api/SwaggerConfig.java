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
            .title ("Busca CEP Api")
            .description ("Essa é uma API de desenvolvida para busca de logradouros e ceps. No momento suporte à busca de ceps e logradouros do estado de Minas Gerais.")
            .version("1.0.0")
            .contact(new Contact().email("marcotulio@frwk.com.br").name("Framework - Marco Tulio Moreira"));
    }
	
}