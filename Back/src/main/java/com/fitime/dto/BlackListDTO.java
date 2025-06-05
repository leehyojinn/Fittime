package com.fitime.dto;

import java.sql.Date;

public class BlackListDTO {
	
	private int blacklist_idx;
	private int report_idx;
	private String target_id;
	private Date reg_date;
	private String name;
	
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBlacklist_idx() {
		return blacklist_idx;
	}
	public void setBlacklist_idx(int blacklist_idx) {
		this.blacklist_idx = blacklist_idx;
	}
	public int getReport_idx() {
		return report_idx;
	}
	public void setReport_idx(int report_idx) {
		this.report_idx = report_idx;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	
}
