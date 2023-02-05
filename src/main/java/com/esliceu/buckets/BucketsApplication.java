package com.esliceu.buckets;

import com.esliceu.buckets.interceptors.LoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BucketsApplication implements WebMvcConfigurer {

	 public static void main(String[] args) {
		SpringApplication.run(BucketsApplication.class, args);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/objects")
				.addPathPatterns("/deleteBucket/*")
				.addPathPatterns("/createObject/*")
				.addPathPatterns("/deleteObject/*")
				.addPathPatterns("/updateObject/*")
				.addPathPatterns("/updateBucket/*")
				.addPathPatterns("/settings");
	}
}
