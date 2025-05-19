package com.fitime.profile;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileDAO {

	int updateUserProfile(Map<String, Object> param);

	int updateTrainerProfile(Map<String, Object> param);
	
	int updateCenterProfile(Map<String, Object> param);

	void profileSave(String id, String filename);

	int profileSearch(String id);
	
	void profileUpdate(String id, String filename);

	void ImgSave(String user_id, String filename);
	
	List<String> ImgSearch(String id);
	
	void ImgDel(String id);
	
	List<String> getFileNames(String id);

	String getFileName(String id);
	
	Map<String, Object> detailUserProfile(String id);

	Map<String, Object> detailTrainerProfile(String id);

	Map<String, Object> detailCenterProfile(String id);


}
