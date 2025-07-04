package com.fitime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import com.example.util.Jwt;

@SpringBootApplication
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 여기서 Key 초기화
        if (Jwt.getPriKey() == null) {
            Jwt.setPriKey();
        }
        return builder.sources(Application.class);
    }
	
    public static void main(String[] args) {
        // 내장 톰캣 실행 시에도 Key 초기화
        if (Jwt.getPriKey() == null) {
            Jwt.setPriKey();
        }
        SpringApplication.run(Application.class, args);
    }

}
