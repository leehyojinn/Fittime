package com.fitime.dto;

public class FileImageDTO {
    private int fileIdx;
    private String fileName;
    private String category;
    private int postIdx;
    private int reportIdx;
    private int boardIdx;

    public int getFileIdx() {
        return fileIdx;
    }

    public void setFileIdx(int fileIdx) {
        this.fileIdx = fileIdx;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPostIdx() {
        return postIdx;
    }

    public void setPostIdx(int postIdx) {
        this.postIdx = postIdx;
    }

    public int getReportIdx() {
        return reportIdx;
    }

    public void setReportIdx(int reportIdx) {
        this.reportIdx = reportIdx;
    }

    public int getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(int boardIdx) {
        this.boardIdx = boardIdx;
    }
}
