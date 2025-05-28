package com.fitime.dto;

public class CenterRatingDTO {
    private String center_id;
    private String center_name;
    private String introduction;
    private Double avg_rating;
    private Integer rating_count;
    private String profile_image;
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getCenter_id() {
		return center_id;
	}
	public void setCenter_id(String center_id) {
		this.center_id = center_id;
	}
	public String getCenter_name() {
		return center_name;
	}
	public void setCenter_name(String center_name) {
		this.center_name = center_name;
	}

	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Double getAvg_rating() {
		return avg_rating;
	}
	public void setAvg_rating(Double avg_rating) {
		this.avg_rating = avg_rating;
	}
	public Integer getRating_count() {
		return rating_count;
	}
	public void setRating_count(Integer rating_count) {
		this.rating_count = rating_count;
	}
}
