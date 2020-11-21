package com.github.lassulfi.tour.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    private fun getAPIMetadata() = ApiInfoBuilder()
            .title("API da Empresa de Turismo ACME")
            .description("API para cadastro, consulta, atualização e exclusão de promoções")
            .version("1.0.0")
            .build();

    @Bean
    fun getSwaggerDocket() = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.github.lassulfi.tour.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getAPIMetadata())
}