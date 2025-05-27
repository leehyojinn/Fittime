package com.fitime.profile;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fitime.dto.Profile_fileDTO;

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

	int delProfileImg(String id);

	List<Profile_fileDTO> photoList(String id);

	Map<String, String> getImg(int profile_file_idx);

	List<String> getTags(String id);

	int insertTag(@Param("user_id") String user_id,@Param("tags") List<Number> tags);

	void delTag(String string);

	List<Map<String, Object>> trainerTags();

	List<Map<String, Object>> centerTags();

	int insertTrainerTags(String id, String tag);

	int insertCenterTags(String id, String tag);







}
