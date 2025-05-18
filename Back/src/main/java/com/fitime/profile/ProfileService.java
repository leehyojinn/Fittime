package com.fitime.profile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileService {

	@Autowired ProfileDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	String root = "C:/img/profile/";
	
	public boolean fileSave(String user_id, MultipartFile file) {
		boolean success = false;
		String filename = user_id+"_profile"; // 프로필 이미지 덮어쓰기를 위해 정해진 이름으로 저장
		
		try {
			byte[] arr = file.getBytes();
			Path path = Paths.get("C:/img/profile/"+filename);
			Files.write(path, arr);
			dao.profileSave(user_id,filename);
			success=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return success;
	}
	
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
				Path path = Paths.get(root+filename);
				Files.write(path, arr);
				dao.profileSave(user_id, filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return success;
	}
	
	public ResponseEntity<Resource> getFile(Map<String, String> param) {
		Resource res = null;
		HttpHeaders headers = new HttpHeaders();
		
		int level =Integer.parseInt(param.get("user_level"));
		String fileName = "";
		
		if(level == 3) { // Center 일 경우
			List<Map<String, String>> fileNames = dao.getFileNames(param.get("center_id"));
			// 대표이미지 + 프로필의 10장 이미지
			String file_name = "";
			for(Map<String, String>map : fileNames) {
				file_name = map.get("file_name");
				if(file_name.contains(param.get("center_id"))) { // 11개의 이미지 중 이름에 center_id 를 포함한 이미지(대표 이미지)
					fileName=file_name;
				}
			}
		} else { // User or Trainer 일 경우
			Map<String, String> map = dao.getFileName(param.get("user_id"));
			fileName = map.get("file_name");
		}
		
		res = new FileSystemResource("C:/img/"+fileName);
		
		try {
			String content_type = Files.probeContentType(Paths.get("C:/img/"+fileName));
			headers.add("Content-Type", content_type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(res,headers,HttpStatus.OK);
	}

	
	
	public Map<String, Object> userProfile(Map<String, String> param) {
		Map<String, Object>map = new HashMap<String, Object>();
		Map<String, Object>userProfile = dao.userProfile(param);
		map.put("userProfile", userProfile);
		ResponseEntity<Resource> file = getFile(param);
		map.put("image", userProfile);
		logger.info("result : {}",map);
		return map;
	}

	
	
//	public boolean insertTrainerProfile(MultipartFile file, Map<String, Object> param) {
//		int row = dao.insertTrainerProfile(param);
//		boolean save_success = true; // 파일 save 성공 여부, file = null 일 경우 true, save 실패 시에만 false
//		if(file!=null) {
//			save_success = fileSave((String)param.get("user_id"),file);
//		}
//		return row > 0 && save_success;
//	}
	
	// 사진 1장 저장 (프로필 이미지, 대표이미지)
	

//	// 센터 프로필 + 대표이미지 저장
//	public boolean insertCenterProfile(MultipartFile[] files, MultipartFile file, Map<String, Object> param) {
//		int row = dao.insertCenterProfile(param);
//		boolean image_save = true;
//		boolean profile_save = true;
//		if(file!=null) { //대표 이미지가 들어온 경우
//			profile_save = fileSave((String)param.get("center_id"),file);
//		}
//		if(files!=null) { // 센터 이미지가 들어온 경우
//			image_save = fileSave((String)param.get("center_id"),files);
//		}
//		return row > 0 && image_save && profile_save;
//	}
//	
//	public boolean updateUserProfile(MultipartFile file, Map<String, Object> param) {
//		int row = dao.updateUserProfile(param);
//		boolean save_success = true;
//		if(row>0 && file != null) { // 수정 성공 + 프로필 이미지가 들어온 경우
//			save_success = fileSave((String)param.get("user_id"),file);
//		}
//		return row > 0 && save_success;
//	}
//
//	public boolean updateTrainerProfile(MultipartFile file, Map<String, Object> param) {
//		int row = dao.updateTrainerProfile(param); // 트레이너 테이블 수정
//		boolean save_success = true;
//		if(row>0) { // 트레이너 테이블 수정 성공 시
//			row = dao.updateUserProfile(param); // 유저 테이블 수정
//			if(file != null) { // 프로필 이미지가 들어온 경우
//				save_success = fileSave((String)param.get("user_id"),file);
//			}
//		}
//		
//		return row > 0 && save_success;
//	}
//	
//	public boolean updateCenterProfile(MultipartFile[] files, MultipartFile file, Map<String, Object> param) {
//		int row = dao.updateCenterProfile(param);
//		boolean image_save = true;
//		boolean profile_save = true;
//		if(row>0) {
//			if(file!=null) { // 프로필 수정 성공 + 대표이미지가 들어온 경우
//				profile_save = fileSave((String)param.get("center_id"),file);
//			}
//			if(files!=null) { // 프로필 수정 성공 + 이미지가 들어온 경우
//				image_save = fileSave((String)param.get("center_id"),files);
//			}
//		}
//		return row > 0 && image_save && profile_save;
//	}

	
	
	
	// 이미지 저장
	
	
	// 이미지 수정
	// 이미지 삭제
	// 이미지 불러오기
	//public ResponseEntity<Resource> getFile
	
	
}
