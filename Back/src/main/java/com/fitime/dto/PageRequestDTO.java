package com.fitime.dto;

public class PageRequestDTO {

	private int page;
	private int size;
	
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public Object getOffset() {
		return (page - 1) * size;
	}
}
