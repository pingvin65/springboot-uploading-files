package com.uploading.files;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//          .addResourceHandler("/resources/**")
//          .addResourceLocations("/resources/"); 
//    }
//	private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	private final String uploadDirectory = "uploads";
	private final String separator = File.separator;
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/uploads/**")
				.addResourceLocations("file:" + getAbslutePath(this.uploadDirectory));
	}
	
	private String getAbslutePath(String uploadDirectory) {
		return System.getProperty("user.dir") + this.separator + uploadDirectory + this.separator;
	}
}
