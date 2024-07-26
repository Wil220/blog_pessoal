package com.generation.blogpessoal.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI().info(new Info().title("Projeto blog pessoal")
				.description("Projeto Blog Pessoal - Wilker Silva").version("v0.0.1")
				.license(new License().name("Wilker Silva")
						.url("https://https://github.com/Wil220"))
				.contact(new Contact().name("Wilker Silva")
						.url("https://www.linkedin.com/in/wilker-silva-3a0ab2273/").email("wilker.silva0714@gmail.com")))
				.externalDocs(new ExternalDocumentation().description("Github")
						.url("https://github.com/Wil220/blog_pessoal"));
	}

	   @Bean
	    OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
	        return openApi -> {
	            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
	                    .forEach(operation -> {

	                        ApiResponses apiResponses = operation.getResponses();

	                        apiResponses.addApiResponse("200", createApiResponse("Requisição realizada com sucesso"));
	                        apiResponses.addApiResponse("201", createApiResponse("Objeto persistido com sucesso"));
	                        apiResponses.addApiResponse("204", createApiResponse("Objeto excluído com sucesso"));
	                        apiResponses.addApiResponse("400", createApiResponse("Erro na requisição do cliente"));
	                        apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado"));
	                        apiResponses.addApiResponse("403", createApiResponse("Acesso proibido"));
	                        apiResponses.addApiResponse("404", createApiResponse("Não encontrado"));
	                        apiResponses.addApiResponse("500", createApiResponse("Erro interno no servidor"));
	                    }));
	        };
	    }

	    private ApiResponse createApiResponse(String description) {
	        return new ApiResponse().description(description);
	    }
	}