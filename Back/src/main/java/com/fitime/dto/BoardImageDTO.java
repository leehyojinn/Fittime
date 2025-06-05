package com.fitime.dto;

import java.sql.Timestamp;

public class BoardImageDTO {

    private int fileIdx;       // ✅ 파일 고유 ID (옵션)
    private int boardIdx;      // 게시글 번호 (FK)
    private String fileName;   // 저장된 파일명
    private Timestamp regDate; // 등록일 (옵션)

    public int getFileIdx() {
        return fileIdx;
    }

    public void setFileIdx(int fileIdx) {
        this.fileIdx = fileIdx;
    }

    public int getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(int boardIdx) {
        this.boardIdx = boardIdx;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
}
