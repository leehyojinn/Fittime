package com.fitime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.util.Jwt;

@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 외부 톰캣(WAR) 배포 시 Key 초기화
        if (Jwt.getPriKey() == null) {
            Jwt.setPriKey();
        }
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        // 내장 톰캣(JAR) 실행 시 Key 초기화
        if(Jwt.getPriKey()==null) {
            Jwt.setPriKey();
        }
        SpringApplication.run(Application.class, args);
    }
}
