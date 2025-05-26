package com.fitime.dto;

import java.sql.Date;

public class BuyListDTO {

	private int buy_idx;
	private String user_id;
	private int product_idx;
	private Date reg_date;
	private int payment_idx;
	public int getBuy_idx() {
		return buy_idx;
	}
	public void setBuy_idx(int buy_idx) {
		this.buy_idx = buy_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getPayment_idx() {
		return payment_idx;
	}
	public void setPayment_idx(int payment_idx) {
		this.payment_idx = payment_idx;
	}
	
}
