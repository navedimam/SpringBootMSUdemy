package com.net;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
					title ="Open API documentation For User Service",
					description = "Documentation for user service ",
					version = "1.0",
					contact = @Contact(
								name = "gaurav",
								email = "gaurv@gmail.com",
								url = "url.url"
							),
					license = @License(
								name = "Licence name",
								url = "Licenceurl"
							)
				),
		externalDocs = @ExternalDocumentation(
					url = "External-documentation",
					description = "this is external documentation"
				)
)
public class CrudRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRestApiApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}



