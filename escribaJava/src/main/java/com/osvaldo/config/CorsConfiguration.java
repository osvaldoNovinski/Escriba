package com.osvaldo.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
				.allowedOrigins("*")
				.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
	}

	@Bean
	public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
		return new CustomScopeRegisteringBeanFactoryPostProcessor();
	}
}
