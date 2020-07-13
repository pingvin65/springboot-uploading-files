# springboot-uploading-files
This project uses [Apache Commons IO](https://commons.apache.org/proper/commons-io/) to upload a file, Thymeleaf and Spring Boot starter.
Commons IO is a library of utilities to assist with developing IO functionality. 


## Controller
The controller has two methods home and uploud. The home method uses Commons IO to list files in a directory. The upload method uses Commons IO for the upload file.
```Java
@Controller
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";


	@RequestMapping("/")
	public String home(Model model) throws IOException {
		File dir = new File("uploads");

		logger.info("Getting all files in " + dir.getCanonicalPath() + " including those in subdirectories");
		List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			logger.info("file: " + file.getCanonicalPath() + " " + FileUtils.byteCountToDisplaySize(FileUtils.sizeOf(file)));

		}
		model.addAttribute("files", files);

		return "uploadview";
	}

	@RequestMapping("/upload")
	public String upload(Model model, @RequestParam("files") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes) throws IOException {

		if(multipartFile.isEmpty()) {
			redirectAttributes.addFlashAttribute("warning",
					"There is no file to upload !");
			return "redirect:/";
		}
		
		InputStream fileStream = multipartFile.getInputStream();
		File targetFile = new File(
				uploadDirectory + System.getProperty("file.separator") + multipartFile.getOriginalFilename());
		FileUtils.copyInputStreamToFile(fileStream, targetFile);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + targetFile.getName() + "!");
		model.addAttribute("message", "Successfully uploaded files ");
		return "redirect:/";
	}
}
```

![image001](./uploads/image001.png)

![image002](./uploads/image002.png)

![image003](./uploads/image002.png)