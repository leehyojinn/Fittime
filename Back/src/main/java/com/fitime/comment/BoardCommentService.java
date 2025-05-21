package com.fitime.comment;

import java.util.List;
import com.fitime.dto.BoardCommentDTO;

public interface BoardCommentService {
    void insertComment(BoardCommentDTO dto);
    List<BoardCommentDTO> getCommentList(int boardIdx);
	void updateComment(BoardCommentDTO dto);
	void deleteComment(int commentIdx);
}
