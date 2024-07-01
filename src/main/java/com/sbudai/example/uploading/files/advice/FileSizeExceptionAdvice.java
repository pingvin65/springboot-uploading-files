package com.sbudai.example.uploading.files.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileSizeExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(FileSizeExceptionAdvice.class);

	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxFileSize;

	
	/**
	 * 
	 * @param exc MaxUploadSizeExceededException
	 * @param redirectAttributes RedirectAttributes
	 * @return String
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exc, RedirectAttributes redirectAttributes) {

		String message = "Attempt to upload file with the size exceeded max allowed value = " + maxFileSize + ".";
		logger.error("handleFileSizeException ffffffff {} ----- {}", exc.getMessage(), exc.getCause().getMessage());
		redirectAttributes.addFlashAttribute("warning", message);
		return "redirect:/";

	}
}
