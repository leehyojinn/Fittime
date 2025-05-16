package com.fitime.member;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.util.Jwt;
@CrossOrigin
@RestController
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberService service;
	Map<String, Object> result = null;
	
	// 회원가입
	@PostMapping(value="/join")
	public Map<String, Object> join(@RequestBody Map<String, String> params){
		logger.info("가입 정보 : {}",params);
		result = new HashMap<String, Object>();
		boolean success = service.join(params);
		result.put("success", success);
		return result;
	}
	
	// 로그인
	@PostMapping(value="/login")
	public Map<String, Object> login(@RequestBody Map<String, String> params){
		logger.info("로그인 정보 : "+params);
		result = new HashMap<String, Object>();
		boolean success = service.login(params);
		boolean login = false;
		String loginId = params.get("user_id");
		if(!loginId.equals("") && loginId!=null) {
			int user_level = service.get_level(params);
			String token = Jwt.setToken("user_id",params.get("user_id"));
			result.put("token", token);
			result.put("user_level", user_level);
			result.put("success", success);
			login = true;
		}
		result.put("login", login);
		return result;
	}
	
	// 아이디 중복체크
	@PostMapping(value="/overlay/id")
	public Map<String, Object> overlayId(@RequestBody Map<String, Object> param){
		logger.info("아이디 중복 체크 : "+param);
		result = new HashMap<String, Object>();
		boolean success = service.overlayId(param);
		result.put("use", success);		
		return result;
	}
	
	// 이메일 중복체크
	@PostMapping(value="/overlay/email")
	public Map<String, Object> overlayEmail(@RequestBody Map<String, Object> param){
		logger.info("이메일 중복 체크 : "+param);
		result = new HashMap<String, Object>();
		boolean success = service.overlayEmail(param);
		result.put("use", success);
		return result;
	}
	
	// 회원탈퇴
	@PostMapping(value="/unregiste")
	public Map<String, Object> unregiste(@RequestBody Map<String, Object> param){
		logger.info("회원 탈퇴 : "+param);
		result = new HashMap<String, Object>();
		boolean success = service.unregiste(param);
		result.put("success", success);
		return result;
	}
	
	// 아이디 찾기
	@PostMapping(value="/find/id")
	public Map<String, Object> findId(@RequestBody Map<String, Object> param){
		logger.info("아이디 찾기 요청 : "+param);
		result = new HashMap<String, Object>();
		String email = (String) param.get("email");
		String userId = service.findId(email);
		if(!userId.equals("") || userId != null) {
			result.put("success", true);
			result.put("user_id", userId);
		}
		return result;
	}
	
	// 비밀번호 찾기
	@PostMapping(value="/find/password")
	public Map<String, Object> findPw(@RequestBody Map<String, Object> param){
		logger.info("패스워드 찾기 요청 : "+param);
		result = new HashMap<String, Object>();
		String userPw = service.findPw(param);
		result.put("success", true);
		result.put("password", userPw);
		return result;
	}
	
	
	
}
