package com.fitime.reply;

import java.util.List;
import com.fitime.dto.BoardReplyDTO;

public interface BoardReplyService {
    void insertReply(BoardReplyDTO dto);
    List<BoardReplyDTO> getRepliesByComment(int commentIdx);
    void updateReply(BoardReplyDTO dto);
    void deleteReply(int replyIdx);
    List<BoardReplyDTO> getRepliesByParent(int parentReplyIdx);
    List<BoardReplyDTO> getRootComments(int boardIdx);
}
