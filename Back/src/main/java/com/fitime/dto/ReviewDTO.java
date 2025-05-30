package com.fitime.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ReviewDTO {

	private int review_idx;
	private String user_id;
	private String target_id;
	private int reservation_idx;
	private double rating;
	private String content;
	private Date reg_date;

	private Double avg_rating;
	private Integer rating_count;
	
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
	private MultipartFile[] files;
	
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public int getReview_idx() {
		return review_idx;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	public int getReservation_idx() {
		return reservation_idx;
	}
	public void setReservation_idx(int reservation_idx) {
		this.reservation_idx = reservation_idx;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
}
