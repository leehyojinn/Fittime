package com.fitime.member;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberMapper mapper;

	public boolean join(Map<String, String> params) {
		int row = mapper.join(params);
		boolean success = false;
		String level = params.get("user_level");
		switch (level) {
		case "2":
			success = makeProfile(params.get("user_id"), level);
			break;
		case "3":
			success = makeProfile(params.get("user_id"), level);
			break;
		default:
			success = true;
			break;
		}
		return row>0 && success;
	}

	public boolean login(Map<String, String> params) {
		int row = mapper.login(params);
		return row>0;
	}

	public int get_level(Map<String, String> params) {
		Integer level =  mapper.get_level(params);
		return (level != null) ? level : -1;
	}

	public boolean overlayId(Map<String, Object> param) {
		int cnt = mapper.overlayId(param);
		return cnt == 0;
	}

	public boolean overlayEmail(Map<String, Object> param) {
		int cnt = mapper.overlayEmail(param);
		return cnt == 0;
	}

	public boolean unregiste(Map<String, Object> param) {
		int row = mapper.unregiste(param);
		return row>0;
	}

	public String findId(String email) {
		return mapper.findId(email);
	}

	public String findPw(Map<String, Object> param) {
		return mapper.findPw(param);
	}

	public boolean makeProfile(String id, String level) {
		int row = 0;
		switch (level) {
		case "2" : 
			row = mapper.makeTrainer(id);
			break;
		case "3" :
			row = mapper.makeCenter(id);
			break;
		default:
			break;
		}
		return row > 0 ? true : false;
	}

	public String findEmailByUserId(String user_id) {
		return mapper.findEmailByUserId(user_id);
	}

	public boolean updateUserPassword(String user_id, String tempPwd) {
		int row = mapper.updateUserPassword(user_id,tempPwd);
		return row > 0;
	}
	
}
