package com.backend.hotelservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(getIgnoredTypes())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .globalResponseMessage(RequestMethod.GET,getCustomizedResponseMessages());
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("YGC App")
                .description("application for")
                .version("1.0.0")
                .build();
    }

    private List<ResponseMessage> getCustomizedResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(500).message("Internal Sever Error!!").responseModel(new ModelRef("Error")).build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("Not Found Exception!!").build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message("Forbidden!!").build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized!!").build());
        return responseMessages;
    }

    private List<ResponseMessage> postCustomizedResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(500).message("Internal Sever Error!!").responseModel(new ModelRef("Error")).build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("Not Found Exception!!").build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message("Forbidden!!").build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized!!").build());
        return responseMessages;
    }

    private Class<?>[] getIgnoredTypes() {
        List<Class<?>> list = new ArrayList<>();
        list.add(Authentication.class);
        list.add(Pageable.class);
        Class<?>[] itemsArray = new Class[list.size()];
        itemsArray = list.toArray(itemsArray);
        return itemsArray;
    }
}