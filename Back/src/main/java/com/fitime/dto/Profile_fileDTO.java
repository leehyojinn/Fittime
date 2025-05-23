package com.fitime.dto;

public class Profile_fileDTO {

	private int profile_file_idx;
	private String user_id;
	private String file_name;
	private String category;

	public int getProfile_file_idx() {
		return profile_file_idx;
	}

	public void setProfile_file_idx(int profile_file_idx) {
		this.profile_file_idx = profile_file_idx;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
