package com.fitime.dto;

import java.sql.Timestamp;
import java.util.List;

public class BoardDTO {

    
    private String userId;
    private String title;
    private String content;
    private Timestamp regDate;
    private String category;
    private List<String> imageList;
    private int boardIdx;
    
    
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getBoardIdx() {
		return boardIdx;
	}

	
}
