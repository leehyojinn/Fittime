package com.fitime.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fitime.dto.BoardReplyDTO;

import java.util.List;

@RestController
@RequestMapping("/board/reply")
@CrossOrigin(
	    origins = "http://192.168.0.114:3000",
	    allowedHeaders = "*",
	    allowCredentials = "true"
	)
public class BoardReplyController {

    @Autowired
    private BoardReplyService service;

    @PostMapping("/write")
    public void insertReply(@RequestBody BoardReplyDTO dto) {
        service.insertReply(dto);
    }

    @PostMapping("/list/{commentIdx}")
    public List<BoardReplyDTO> getReplies(@PathVariable int commentIdx) {
        return service.getRepliesByComment(commentIdx);
    }

    @PostMapping("/update")
    public void updateReply(@RequestBody BoardReplyDTO dto) {
        service.updateReply(dto);
    }

    @PostMapping("/delete/{replyIdx}")
    public void deleteReply(@PathVariable int replyIdx) {
        service.deleteReply(replyIdx);
    }

    @PostMapping("/list/comments/{boardIdx}")
    public List<BoardReplyDTO> getComments(@PathVariable int boardIdx) {
        return service.getRootComments(boardIdx);
    }
}
