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
		return row>0;
	}

	public boolean login(Map<String, String> params) {
		int row = mapper.login(params);
		return row>0;
	}

	public int get_level(Map<String, String> params) {
		return mapper.get_level(params);
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

}
