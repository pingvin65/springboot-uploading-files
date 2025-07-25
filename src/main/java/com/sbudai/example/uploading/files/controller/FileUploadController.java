package com.sbudai.example.uploading.files.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	public static String uploadDirectory = System.getProperty("user.dir") + File.separator + "uploads";

	/**
	 * 
	 * @param model Model
	 * @return String
	 * @throws IOException
	 */
	@GetMapping("/")
	public String home(Model model) throws IOException {
		File dir = new File("uploads");

		logger.info("Getting all files in " + dir.getCanonicalPath() + " including those in subdirectories");
		List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			logger.info("file: " + file.getCanonicalPath() + " "
					+ FileUtils.byteCountToDisplaySize(FileUtils.sizeOf(file)));

		}

		model.addAttribute("files", files);

		return "uploadview";
	}

	/**
	 * 
	 * @param model              Model
	 * @param multipartFile      MultipartFile
	 * @param redirectAttributes RedirectAttributes
	 * @return String
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public String upload(Model model, @RequestParam("files") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes) throws IOException {
		logger.error("get Bytes {}", multipartFile.getBytes().length);
		if (multipartFile.isEmpty()) {
			redirectAttributes.addFlashAttribute("warning", "There is no file to upload !");
			return "redirect:/";
		}

		InputStream fileStream = multipartFile.getInputStream();
		File targetFile = new File(
				uploadDirectory + System.getProperty("file.separator") + multipartFile.getOriginalFilename());
		FileUtils.copyInputStreamToFile(fileStream, targetFile);
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + targetFile.getName() + "!");
		model.addAttribute("message", "Successfully uploaded files ");
		return "redirect:/";
	}

}
