package com.br.testeVr.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Teste VR")
                        .version("1.0.0")
                        .description("Teste VR API")
                        .contact(new Contact()
                                .name("Teste VR")
                                .url("https://github.com/ybueno16")
                                .email("yuribueno27@gmail.com"))
                        .license(new License()
                                .name("The Unlicense")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")))
                .addServersItem(new Server().url("http://localhost:8080")
                        .description("Servidor de desenvolvimento"));
    }


}