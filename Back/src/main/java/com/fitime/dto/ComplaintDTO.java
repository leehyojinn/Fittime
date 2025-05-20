package com.fitime.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ComplaintDTO {

	private int report_idx;
	private String report_id;
	private String admin_id;
	private int review_idx;
	private String report_text;
	private String reason;
	private String process_history;
	private String status;
	private String reg_date;
	private List<MultipartFile> files;
	private List<String> images;
	
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public int getReport_idx() {
		return report_idx;
	}
	public void setReport_idx(int report_idx) {
		this.report_idx = report_idx;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public int getReview_idx() {
		return review_idx;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}
	public String getReport_text() {
		return report_text;
	}
	public void setReport_text(String report_text) {
		this.report_text = report_text;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getProcess_history() {
		return process_history;
	}
	public void setProcess_history(String process_history) {
		this.process_history = process_history;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
