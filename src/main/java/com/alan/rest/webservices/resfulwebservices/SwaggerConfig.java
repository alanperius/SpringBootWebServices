package com.alan.rest.webservices.resfulwebservices;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact(
            "Alan Perius", "alan.com", "alan.perius.mv@gmail.com");

    public static final ApiInfo DEFAULT_INFO = new ApiInfo(
            "Any title",
            "Any description",
            "1.0.0",
            "Any termsOfServiceUrl",
            DEFAULT_CONTACT,
            "Any license",
            "Any licenseUrl",
            Lists.newArrayList((VendorExtension) null)
            );

    private static final Set<String> DEFAULT_PRODUCES_CONSUMES =
            new HashSet<>(Arrays.asList("application/json",
                    "application/xml"));


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_INFO)
                .produces(DEFAULT_PRODUCES_CONSUMES).consumes(DEFAULT_PRODUCES_CONSUMES);
    }
}
