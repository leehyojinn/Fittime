package com.fitime.board;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {



	public String saveImage(MultipartFile file) {
		if(file.isEmpty()) return null;
		
		String originName = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		String newFileName = uuid + "_" + originName;
		
		try {
			File newFile = new File(newFileName + newFileName);
			file.transferTo(newFile);
			return newFileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
}
