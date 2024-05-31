package com.edix.restful.zaps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = {"com.edix.restful.zaps"})
@CrossOrigin(origins = {"http://localhost:4200"})
public class ZapsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZapsApplication.class, args);
    }
}
