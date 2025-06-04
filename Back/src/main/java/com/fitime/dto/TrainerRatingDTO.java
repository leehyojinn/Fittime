package com.fitime.dto;

public class TrainerRatingDTO {
    private String trainer_id;
    private String name;
    private String career;
    private String profile_image;
    private Double avg_rating;
    private Integer rating_count;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getTrainer_id() {
		return trainer_id;
	}
	public void setTrainer_id(String trainer_id) {
		this.trainer_id = trainer_id;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
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
