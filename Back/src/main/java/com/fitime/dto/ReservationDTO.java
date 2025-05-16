package com.fitime.dto;

public class ReservationDTO {
	
	private int reservation_idx;
	private String user_id;
	private String trainer_id;
	private String center_id;
	private int product_idx;
	private String date;
	private String start_time;
	private String end_time;
	private String status;
	
	public int getReservation_idx() {
		return reservation_idx;
	}
	public void setReservation_idx(int reservation_idx) {
		this.reservation_idx = reservation_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTrainer_id() {
		return trainer_id;
	}
	public void setTrainer_id(String trainer_id) {
		this.trainer_id = trainer_id;
	}
	public String getCenter_id() {
		return center_id;
	}
	public void setCenter_id(String center_id) {
		this.center_id = center_id;
	}
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
