package com.fitime.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "*")
public class ImageController {

	@Autowired ImageService imgeService;
	
	@PostMapping(value = "/upload")
	public String uploadImge(@RequestParam("uploadFile") MultipartFile uploadFile) {
		String fileName = imgeService.saveImage(uploadFile);
		return fileName != null ? fileName : "fail";
	}
	
}
