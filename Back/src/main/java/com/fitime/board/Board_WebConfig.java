package com.fitime.board;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Board_WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ./upload/ 폴더에 저장된 파일을 /upload/** URL로 접근할 수 있게 설정
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///./upload/");
    }
}
