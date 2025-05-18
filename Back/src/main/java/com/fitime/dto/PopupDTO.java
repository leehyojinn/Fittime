package com.fitime.dto;

import org.springframework.web.multipart.MultipartFile;

public class PopupDTO {

    private Integer popup_idx;        
    private String user_id;           
    private String title;            
    private String start_date;        
    private String end_date;          
    private String position_top;      
    private String position_left;     
    private String position_right;    
    private String position_bottom;   
    private String popup_width;       
    private MultipartFile popup_image;
    private String file_name;
	private String popup_height;      
    private Boolean use_toggle;

    
    public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public MultipartFile getPopup_image() {
		return popup_image;
	}
	public void setPopup_image(MultipartFile popup_image) {
		this.popup_image = popup_image;
	}

	public Integer getPopup_idx() {
		return popup_idx;
	}
	public void setPopup_idx(Integer popup_idx) {
		this.popup_idx = popup_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getPosition_top() {
		return position_top;
	}
	public void setPosition_top(String position_top) {
		this.position_top = position_top;
	}
	public String getPosition_left() {
		return position_left;
	}
	public void setPosition_left(String position_left) {
		this.position_left = position_left;
	}
	public String getPosition_right() {
		return position_right;
	}
	public void setPosition_right(String position_right) {
		this.position_right = position_right;
	}
	public String getPosition_bottom() {
		return position_bottom;
	}
	public void setPosition_bottom(String position_bottom) {
		this.position_bottom = position_bottom;
	}
	public String getPopup_width() {
		return popup_width;
	}
	public void setPopup_width(String popup_width) {
		this.popup_width = popup_width;
	}
	public String getPopup_height() {
		return popup_height;
	}
	public void setPopup_height(String popup_height) {
		this.popup_height = popup_height;
	}
	public Boolean getUse_toggle() {
		return use_toggle;
	}
	public void setUse_toggle(Boolean use_toggle) {
		this.use_toggle = use_toggle;
	}


    
	
}
