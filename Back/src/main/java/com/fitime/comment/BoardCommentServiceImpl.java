package com.fitime.comment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitime.dto.BoardCommentDTO;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {

    @Autowired
    private BoardCommentDAO dao;

    @Override
    public void insertComment(BoardCommentDTO dto) {
        dao.insertComment(dto);
    }

    @Override
    public List<BoardCommentDTO> getCommentList(int boardIdx) {
        return dao.selectCommentList(boardIdx);
    }
    
    @Override
    public void updateComment(BoardCommentDTO dto) {
        dao.updateComment(dto);
    }
    
    @Override
    public void deleteComment(int commentIdx) {
        dao.deleteComment(commentIdx);
    }
    
}
