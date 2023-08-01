package com.ortizmiguelangel.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Prices API")
                        .description("Prices API challenge")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Miguel Ángel Ortiz Amores")
                                .email("karmic.323@gmail.com")));
    }
}
