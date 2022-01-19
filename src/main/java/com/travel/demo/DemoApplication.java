package com.travel.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class DemoApplication{
    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }
}
