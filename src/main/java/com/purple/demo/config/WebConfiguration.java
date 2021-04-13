package com.purple.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Value("${spring.servlet.multipart.location}") 
   private String uploadImagePath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {	
			
		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:///" + uploadImagePath + "/images/")
				.setCachePeriod(3600)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());

	}	
}