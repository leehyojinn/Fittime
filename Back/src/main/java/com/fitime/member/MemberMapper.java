package com.fitime.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	int join(Map<String, String> params);

	int login(Map<String, String> params);

	int get_level(Map<String, String> params);

	int overlayId(Map<String, Object> param);

	int overlayEmail(Map<String, Object> param);

	int unregiste(Map<String, Object> param);

	String findId(String email);

	String findPw(Map<String, Object> param);
}
