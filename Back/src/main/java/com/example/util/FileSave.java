package com.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileSave {
	String root = "/usr/local/tomcat/webapps/img/"; // 변경해서 사용하세요
	
	// 사진 여러장 저장(센터 이미지)
		public boolean fileSave(String user_id, MultipartFile[] files) {
			boolean success = false;
			String filename = "";
			String ext = "";
			for(MultipartFile file : files) {
				filename = file.getOriginalFilename();
				ext = filename.substring(filename.lastIndexOf("."));
				filename = UUID.randomUUID()+ext; // 덮어쓰기가 필요 없기 때문에 랜덤 ID 로 저장
				
				try {
					byte[] arr = file.getBytes();
					Path path = Paths.get(root+filename); // root (파일 저장 경로 : "/usr/local/tomcat/webapps/img/패키지명/")
					Files.write(path, arr);
					//dao.profileSave(user_id, filename); // 쿼리문 변경해서 사용하세요
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return success;
		}
	
}
