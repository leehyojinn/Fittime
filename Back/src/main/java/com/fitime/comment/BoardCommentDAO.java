package com.fitime.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.BoardCommentDTO;

@Mapper
public interface BoardCommentDAO {
    void insertComment(BoardCommentDTO dto);
    List<BoardCommentDTO> selectCommentList(int boardIdx);
    void updateComment(BoardCommentDTO dto);
    void deleteComment(int commentIdx);
}