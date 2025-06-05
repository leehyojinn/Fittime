package com.fitime.dto;

import java.util.List;

public class BoardDTO {
    private Integer boardIdx;
    private String title;
    private String content;
    private String category;
    private String userId;
    private List<String> imageList;
    private String state = "활성";
    private String regDate;
    
    public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public BoardDTO() {
        // 기본 생성자 - 아무 내용 없어도 됨
    }

    public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getBoardIdx() { return boardIdx; }
    public void setBoardIdx(int boardIdx) { this.boardIdx = boardIdx; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<String> getImageList() { return imageList; }
    public void setImageList(List<String> imageList) { this.imageList = imageList; }
}
