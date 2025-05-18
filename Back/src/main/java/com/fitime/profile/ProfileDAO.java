package com.fitime.profile;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileDAO {

//	int insertTrainerProfile(Map<String, Object> param);
//
//	int insertCenterProfile(Map<String, Object> param);
//
//	int updateUserProfile(Map<String, Object> param);
//
//	int updateTrainerProfile(Map<String, Object> param);
//	
//	int updateCenterProfile(Map<String, Object> param);

	void profileSave(String user_id, String filename);

	List<Map<String, String>> getFileNames(String id);

	Map<String, String> getFileName(String id);
	
	Map<String, Object> userProfile(Map<String, String> param);

	

}
