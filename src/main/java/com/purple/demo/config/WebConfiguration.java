package com.purple.demo.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {	
			
		Path imgUploadDir = Paths.get("images");
		String imgUploadPath = imgUploadDir.toFile().getAbsolutePath();		
		registry.addResourceHandler("/images/**").addResourceLocations("file:/" + imgUploadPath + "/");
	}	
}