package com.fitime.profile;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@RestController
public class ProfileController {

	@Autowired ProfileService service;
	Logger logger = LoggerFactory.getLogger(getClass()); 
	Map<String, Object>result = null;
	
	// 센터 프로필 등록
	@PostMapping(value="/insert/centerProfile")
	public Map<String, Object>insertCenterProfile(MultipartFile[] files,MultipartFile file , @RequestPart Map<String, Object>param){
		logger.info("param : {}",param);
		logger.info("file : {}",file);
		logger.info("files : {}",files);
		result = new HashMap<String, Object>();
		boolean success = service.insertCenterProfile(files,file,param);
		result.put("success", success);
		return result;
	}
	
	// 트레이너 프로필 등록
	@PostMapping(value="/insert/trainerProfile")
	public Map<String, Object>insertTrainerProfile(MultipartFile file, @RequestPart Map<String, Object>param){
		logger.info("param : {}",param);
		logger.info("files : {}",file);
		result = new HashMap<String, Object>();
		boolean success = service.insertTrainerProfile(file,param);
		result.put("success", success);
		return result;
	}
	
	// 회원 프로필 수정
	@PostMapping(value="/update/userProfile")
	public Map<String, Object>updateUserProfile(MultipartFile file , @RequestPart Map<String, Object>param){
		logger.info("param : {}",param);
		logger.info("file : {}",file);
		result = new HashMap<String, Object>();
		boolean success = service.updateUserProfile(file, param);
		result.put("success", success);
		return result;
	}
	
	// 트레이너 프로필 수정
		@PostMapping(value="/update/trainerProfile")
		public Map<String, Object>updateTrainerProfile(MultipartFile file , @RequestPart Map<String, Object>param){
			logger.info("param : {}",param);
			logger.info("file : {}",file);
			result = new HashMap<String, Object>();
			boolean success = service.updateTrainerProfile(file, param);
			result.put("success", success);
			return result;
		}
	
	// 센터 프로필 수정
	@PostMapping(value="/update/centerProfile")
	public Map<String, Object>updateCenterProfile(MultipartFile[] files,MultipartFile file , @RequestPart Map<String, Object>param){
		logger.info("param : {}",param);
		logger.info("file : {}",file);
		logger.info("files : {}",files);
		result = new HashMap<String, Object>();
		boolean success = service.updateCenterProfile(files,file,param);
		result.put("success", success);
		return result;
	}
	
	// 프로필 데이터 가져오기
	// 프로필 이미지가 없을 경우 기본 이미지 가져오기(기본 이미지 : profile_file_idx = 0 or user_id = 사이관리자(5))
	@PostMapping(value="detail/userProfile")
	public Map<String, Object>userProfile(@RequestBody Map<String, String>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		return service.userProfile(param);
	}
	
	
	// 프로필 이미지 삭제 (기본 이미지로 변경)
	
	
	
	
	
	
}
