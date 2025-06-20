package com.fitime.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RestController
public class ProfileController {

	@Autowired ProfileService service;
	Logger logger = LoggerFactory.getLogger(getClass()); 
	Map<String, Object>result = null;
	
	@Transactional
	@PostMapping (value="/update/Profile")
	public Map<String, Object>updateProfile(@RequestPart(value = "files", required = false) MultipartFile[] files,@RequestPart(value = "file", required = false) MultipartFile file ,@RequestPart(value="param") Map<String, Object>param){
		logger.info("param : {}",param);
		logger.info("file : {}",file);
		result = new HashMap<String, Object>();
		boolean success = false;
		int level = (int) param.get("user_level");
		
		logger.info("level = "+level);
		switch (level) {
		case 1: 
			success = service.updateUserProfile(file,param);
			break;
		case 2: 
			success = service.updateTrainerProfile(files,file,param);
			break;
		case 3: 
			success = service.updateCenterProfile(files,file,param);
			break;
		default:
			break;
		}
		result.put("success", success);
		return result;
	}
	
//	@PostMapping(value="/update/Profile")
//	public Map<String, Object>updateProfile(@RequestBody Map<String, Object>param){
//		logger.info("param : {}",param);
//		result = new HashMap<String, Object>();
//		int level = (int) param.get("user_level");
//		boolean success = false;
//		switch (level) {
//		case 2: 
//			success = service.updateTrainerProfile(param);
//			break;
//		case 3: 
//			success = service.updateCenterProfile(param);
//			break;
//		default:
//			success = service.updateUserProfile(param);
//			break;
//		}
//		result.put("success", success);
//		return result;
//	}
	
	
	// 프로필 데이터 가져오기
	// 프로필 이미지가 없을 경우 기본 이미지 가져오기(기본 이미지 : profile_file_idx = 0 or user_id = 사이관리자(5))
	@PostMapping(value="/detail/profile")
	public Map<String, Object>detailProfile(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		result = service.detailProfile(param);
		return result;
	}
	
	// 프로필 이미지 가져오기
	@GetMapping(value="/profileImg/profile/{user_id}")
	public ResponseEntity<Resource> profileImg(@PathVariable String user_id){
		logger.info("user_id : "+user_id);
//		String id = "";
//		String level = (String)param.get("user_level");
//		switch (level) {
//		case 1:
//			id = (String)param.get("user_id");
//			break;
//		case 2:
//			id = (String)param.get("trainer_id");		
//			break;
//		case 3:
//			id = (String)param.get("center_id");
//			break;
//		default:
//			break;
//		}
		
		return service.getFile(user_id);
	} 
	
	// 프로필 이미지 삭제 (기본 이미지로 변경)
	@Transactional
	@DeleteMapping(value="/del/profileImg")
	public Map<String, Object> delProfileImg(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		String id = (String)param.get("user_id");
		result = new HashMap<String, Object>();
		boolean success = service.delProfileImg(id);
		result.put("success", success);
		return result;
	}
	
	// 센터 이미지 가져오기
	@GetMapping(value="/centerImg/{profile_file_idx}")
	public ResponseEntity<Resource> img(@PathVariable int profile_file_idx){
		return service.getImg(profile_file_idx);
	}
	
	// 태그 리스트 가져오기
	@PostMapping(value="/list/tags/{user_level}")
	public Map<String, Object>tagsList(@PathVariable int user_level){
		logger.info("user_level : "+user_level);
		result = new HashMap<String, Object>();
		List<Map<String, Object>>tags = service.tagsList(user_level);
		result.put("tags", tags);
		return result;
	}
	
	// 태그 입력하기
	@PostMapping(value="/insert/tags")
	public Map<String, Object>insertTags(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.insertTags(param);
		result.put("success", success);
		return result;
	}
	
}

