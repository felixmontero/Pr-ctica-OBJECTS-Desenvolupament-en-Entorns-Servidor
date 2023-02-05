/*package com.esliceu.buckets.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/objects")
                .addPathPatterns("/deleteBucket/*")
                .addPathPatterns("/createObject/*")
                .addPathPatterns("/deleteObject/*")
                .addPathPatterns("/updateObject/*")
                .addPathPatterns("/updateBucket/*");
    }
}*/