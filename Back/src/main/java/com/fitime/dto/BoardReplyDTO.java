package com.fitime.dto;

public class BoardReplyDTO {
	
	private int boardIdx;
    public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	private int replyIdx;
    private int commentIdx;
    private String userId;
    private String content;
    private String regDate;
    private int comment_idx;
  

	public int getComment_idx() {
		return comment_idx;
	}
	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}
	public int getReplyIdx() { return replyIdx; }
    public void setReplyIdx(int replyIdx) { this.replyIdx = replyIdx; }

    public int getCommentIdx() { return commentIdx; }
    public void setCommentIdx(int commentIdx) { this.commentIdx = commentIdx; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }
}
