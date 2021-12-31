package com.zooplus.swagger;

import org.springframework.context.annotation.Configuration;
 import org.springframework.context.annotation.Bean;
 import springfox.documentation.builders.ApiInfoBuilder;
 import springfox.documentation.builders.PathSelectors;
 import springfox.documentation.builders.RequestHandlerSelectors;
 import springfox.documentation.service.ApiInfo;
 import springfox.documentation.service.Contact;
 import springfox.documentation.spi.DocumentationType;
 import springfox.documentation.spring.web.plugins.Docket;
 import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author germanjit singh version 1.0
 * */
 @Configuration
 @EnableSwagger2
public class SwaggerUIConfiguration {

    @Bean
    public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
    .select()
    .apis(RequestHandlerSelectors.basePackage("com.zooplus"))
    .paths(PathSelectors.any())
    .build().apiInfo(apiEndPointInfo());
    }

    public ApiInfo apiEndPointInfo(){
    return new ApiInfoBuilder().title("Spring Boot Rest API")
    .description("Zooplus APP")
    .contact(new Contact("Germanjit Singh", "zooplus-app/getOrderDetails", "sgermanjit@gmail.com"))
    .version("0.0.1-SNAPSHOT")
    .build();
    }

}