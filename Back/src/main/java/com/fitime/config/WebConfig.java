package com.fitime.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	//신고이미지
    	registry.addResourceHandler("/complaint/**")
                .addResourceLocations("file:////usr/local/tomcat/webapps/img/complaint/");
        //팝업이미지
        registry.addResourceHandler("/popup/**")
                .addResourceLocations("file:////usr/local/tomcat/webapps/img/popup/");
        //센터이미지
        registry.addResourceHandler("/center_profile_img/**")
                .addResourceLocations("file:////usr/local/tomcat/webapps/img/profile/");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}
