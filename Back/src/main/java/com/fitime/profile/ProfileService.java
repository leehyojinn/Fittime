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
	
	private boolean fileSave(String id, MultipartFile file) {
		boolean success = true;
		String filename = id+"_profile"; // 프로필 이미지 덮어쓰기를 위해 정해진 이름으로 저장
		
		int row = dao.profileSearch(id);
		
		try {
			byte[] arr = file.getBytes();
			Path path = Paths.get("C:/img/profile/"+filename);
			Files.write(path, arr);
			switch (row) {
			case 0: 
				dao.profileSave(id,filename);
				break;
			case 1:
				dao.profileUpdate(id,filename);
				break;
			default: 
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}
			
		return success;
	}
	
	// 사진 여러장 저장(센터 이미지)
	private boolean fileSave(String user_id, MultipartFile[] files) {
		boolean success = true;
		String filename = "";
		String ext = "";
		for(MultipartFile file : files) {
			filename = file.getOriginalFilename();
			ext = filename.substring(filename.lastIndexOf("."));
			filename = UUID.randomUUID()+ext; // 덮어쓰기가 필요 없기 때문에 랜덤 ID 로 저장
			
			try {
				byte[] arr = file.getBytes();
				Path path = Paths.get("C:/img/img/"+filename);
				Files.write(path, arr);
				dao.ImgSave(user_id, filename);
			} catch (IOException e) {
				e.printStackTrace();
				success = false;
			}
		}
		return success;
	}
	
	private boolean fileDel(String id) {
		boolean success = true;
		List<String> filenames = dao.ImgSearch(id);
		if(filenames != null) {
			for (String filename : filenames) {
				try {
					Path path = Paths.get("C:/img/img/"+filename);
					Files.deleteIfExists(path);
				} catch (IOException e) {
					e.printStackTrace();
					success = false;
				}
			}
			dao.ImgDel(id);
		}
		return success;
	}
	
	
	
//	public ResponseEntity<Resource> getFile(String id) {
//		Resource res = null;
//		HttpHeaders headers = new HttpHeaders();
//		
//		String fileName = dao.getFileName(id);
//			
//		if(fileName != null) {
//			res = new FileSystemResource("C:/img/profile/"+fileName);
//			try {
//				String content_type = Files.probeContentType(Paths.get("C:/img/profile/"+fileName));
//				headers.add("Content-Type", content_type);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
//			res = new FileSystemResource("C:/img/profile/basic");
//			try {
//				String content_type = Files.probeContentType(Paths.get("C:/img/profile/basic"));
//				headers.add("Content-Type", content_type);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return new ResponseEntity<Resource>(res,headers,HttpStatus.OK);
//	}
	
	
	public boolean updateUserProfile(MultipartFile file, Map<String, Object> param) {
		int row = dao.updateUserProfile(param);
		boolean save_success = true;
		if(file != null) {
			save_success = fileSave((String)param.get("user_id"),file);
		}
		return row > 0 && save_success;
	}

	public boolean updateTrainerProfile(MultipartFile file, Map<String, Object> param) {
		int row = dao.updateTrainerProfile(param);
		boolean save_success = true;
		if(file !=null) {
			save_success = fileSave((String)param.get("trainer_id"),file);
		}
		return row>0 && save_success;
	}

	public boolean updateCenterProfile(MultipartFile[] files, MultipartFile file, Map<String, Object> param) {
		int row = dao.updateCenterProfile(param);
		boolean image_save = true;
		boolean profile_save = true;
		boolean image_delete = true;
		if(file!=null) { //대표 이미지가 들어온 경우
			profile_save = fileSave((String)param.get("center_id"),file);
		}
		if(files!=null) { // 센터 이미지가 들어온 경우
			image_delete = fileDel((String)param.get("center_id"));
			image_save = fileSave((String)param.get("center_id"),files);
		}
		return row > 0 && image_save && profile_save && image_delete;
	}
	
	public Map<String, Object> detailUserProfile(String id) {
		
		Map<String, Object>map = new HashMap<String, Object>();
		Map<String, Object>result = dao.detailUserProfile(id);
		map.put("profile", result);
		return map;
	}

	public Map<String, Object> detailProfile(Map<String, Object> param) {
		String level = (String)param.get("user_level");
		String id ="";
		Map<String, Object>result = new HashMap<String, Object>();
		switch (level) {
		case "1":
			id = (String)param.get("user_id");
			result = dao.detailUserProfile(id);
			break;
		case "2":
			id = (String)param.get("trainer_id");
			result = dao.detailTrainerProfile(id);
			break;
		case "3":
			id = (String)param.get("center_id");
			result = dao.detailCenterProfile(id);
			break;
		default:
			break;
		}
		return result;
	}

	public ResponseEntity<Resource> getFile(String id) {
		Resource res = null;
		HttpHeaders headers = new HttpHeaders();
		
		String filename = dao.getFileName(id);
		if(filename == null) {
			filename = "basic.png";
		}
		logger.info("filename = "+filename);
		String path = "C:/img/profile/"+filename;
		
		res = new FileSystemResource(path);
		
		try {
			String content_type = Files.probeContentType(Paths.get(path));
			logger.info(content_type);
			headers.add("Content-type", content_type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<Resource>(res,headers,HttpStatus.OK);
	}

	public boolean delProfileImg(String id) {
		int row = dao.delProfileImg(id);
		return row >0 ? true : false ;
	}

	

	
	
	
	
	// 이미지 저장
	
	
	// 이미지 수정
	// 이미지 삭제
	// 이미지 불러오기
	//public ResponseEntity<Resource> getFile
	
	
}
