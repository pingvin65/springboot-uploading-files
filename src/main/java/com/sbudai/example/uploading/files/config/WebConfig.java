package com.sbudai.example.uploading.files.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	
	private final String uploadDirectory = "uploads";
	private final String separator = File.separator;
	
	/**
	 *  @param registry String ResourceHandlerRegistry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/uploads/**", "/webjars/**")
				.addResourceLocations("file:" + getAbslutePath(this.uploadDirectory), "/webjars/");
	
	}
	
	/**
	 * 
	 * @param uploadDirectory String
	 * @return String
	 */
	private String getAbslutePath(String uploadDirectory) {
		return System.getProperty("user.dir") + this.separator + uploadDirectory + this.separator;
	}
	
}