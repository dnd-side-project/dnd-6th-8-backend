package com.travel;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@OpenAPIDefinition
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}

