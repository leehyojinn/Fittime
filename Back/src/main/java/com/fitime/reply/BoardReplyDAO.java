package com.fitime.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.BoardReplyDTO;

@Mapper
public interface BoardReplyDAO {

    void insertReply(BoardReplyDTO dto);
    List<BoardReplyDTO> selectRepliesByComment(int commentIdx);
    void deleteReply(int replyIdx);
    void updateReply(BoardReplyDTO dto);
    List<BoardReplyDTO> selectRepliesByParent(int replyIdx);
    List<BoardReplyDTO> selectRootComments(int boardIdx);
}