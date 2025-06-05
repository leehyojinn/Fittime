package com.fitime.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

	int join(Map<String, String> params);

	int login(Map<String, String> params);

	Integer get_level(Map<String, String> params);

	int overlayId(Map<String, Object> param);

	int overlayEmail(Map<String, Object> param);

	int unregiste(Map<String, Object> param);

	String findId(String email);

	String findPw(Map<String, Object> param);
	
	int makeTrainer(String id);

	int makeCenter(Map<String, String> params);

	String findEmailByUserId(String user_id);

	int updateUserPassword(@Param("user_id") String userId, @Param("password") String password);

	int get_exerciseLevel(String id);
}
