package com.uploading.files;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.uploading.files.controllers.FileUploadController;


//@Configuration
//@EnableAutoConfiguration
//@ComponentScan({"files","controllers"})
@SpringBootApplication
public class SpringbootUploadingFilesApplication {

	public static void main(String[] args) {
		new File(FileUploadController.uploadDirectory).mkdir();
		SpringApplication.run(SpringbootUploadingFilesApplication.class, args);
	}
	


}
