package com.fitime.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	//신고이미지
    	registry.addResourceHandler("/complaint/**")
                .addResourceLocations("file:///C:/img/complaint/");
        //팝업이미지
        registry.addResourceHandler("/popup/**")
                .addResourceLocations("file:///C:/img/popup/");
        //센터이미지
        registry.addResourceHandler("/center_profile_img/**")
                .addResourceLocations("file:///C:/img/profile/");
    }
}
