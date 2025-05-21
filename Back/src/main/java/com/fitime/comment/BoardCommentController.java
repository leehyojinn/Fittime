package com.fitime.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.fitime.dto.BoardCommentDTO;

import java.util.List;

@Qualifier("boardCommentService")
@RestController
@RequestMapping("/BoardComment")
@CrossOrigin(origins = "*")
public class BoardCommentController {

    @Autowired
    private BoardCommentService service;

    @PostMapping("/insert")
    public void insertComment(@RequestBody BoardCommentDTO dto) {
        service.insertComment(dto);
    }

    @PostMapping("/list/{boardIdx}")
    public List<BoardCommentDTO> getCommentList(@PathVariable int boardIdx) {
        return service.getCommentList(boardIdx);
    }
    
    @PostMapping("/update")
    public void updateComment(@RequestBody BoardCommentDTO dto) {
        service.updateComment(dto);
    }
    
    @PostMapping("/delete/{commentIdx}")
    public void deleteComment(@PathVariable int commentIdx) {
        service.deleteComment(commentIdx);
    }
    
}
