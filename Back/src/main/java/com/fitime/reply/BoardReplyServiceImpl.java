package com.fitime.reply;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitime.dto.BoardReplyDTO;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {

    @Autowired
    private BoardReplyDAO dao;

    @Override
    public void insertReply(BoardReplyDTO dto) {
        dao.insertReply(dto);
    }

    @Override
    public List<BoardReplyDTO> getRepliesByComment(int commentIdx) {
        return dao.selectRepliesByComment(commentIdx);
    }

    @Override
    public void updateReply(BoardReplyDTO dto) {
        dao.updateReply(dto);
    }

    @Override
    public void deleteReply(int replyIdx) {
        dao.deleteReply(replyIdx);
    }

    @Override
    public List<BoardReplyDTO> getRepliesByParent(int parentReplyIdx) {
        return dao.selectRepliesByParent(parentReplyIdx);
    }

    @Override
    public List<BoardReplyDTO> getRootComments(int boardIdx) {
        return dao.selectRootComments(boardIdx);
    }
}
