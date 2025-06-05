package com.fitime.board;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fitime.dto.BoardDTO;
import com.fitime.dto.PageRequestDTO;
import com.fitime.dto.PageResponseDTO;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
public class BoardController {

    @Autowired
    private BoardService service;

    @PostMapping("/list/board")
    public List<BoardDTO> getBoardList(@RequestBody Map<String, String> param) {
        String category = param.get("category");
        return service.getBoardListByCategory(category);
    }

    @PostMapping("/detail/board")
    public BoardDTO getBoardDetail(@RequestBody Map<String, Integer> param) {
        Integer boardIdx = param.get("boardIdx");
        if (boardIdx == null || boardIdx <= 0) return null;
        return service.read(boardIdx);
    }

    @PostMapping("/delete/board")
    public String deleteBoard(@RequestBody Map<String, Integer> param) {
        Integer boardIdx = param.get("boardIdx");
        if (boardIdx == null) return "삭제 실패: boardIdx 없음";
        service.softDelete(boardIdx);
        return "삭제 완료";
    }

    @PostMapping("/board/writeWithFiles/board")
    public ResponseEntity<String> writeWithFiles(
            @RequestPart("boardDTO") BoardDTO boardDTO,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {
        try {
            service.writeWithFiles(boardDTO, files);
            return ResponseEntity.ok("작성 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("작성 실패: " + e.getMessage());
        }
    }
    
    @PostMapping("/update/board")
    public String updateBoard(@RequestBody BoardDTO boardDTO) {
        service.update(boardDTO);
        return "수정 완료";
    }
    
    @PostMapping("/fileList/board")
    public List<String> getFileList(@RequestBody Map<String, Integer> param) {
        int boardIdx = param.get("boardIdx");
        return service.getFileNameByBoardIdx(boardIdx);
    }

    @PostMapping("/pagingList/board")
    public PageResponseDTO<BoardDTO> getPagingList(@RequestBody PageRequestDTO pageRequest) {
        return service.getPagedList(pageRequest);
    }

    @PostMapping("/search/board")
    public List<BoardDTO> search(@RequestBody Map<String, String> param) {
        return service.search(param.get("keyword"));
    }
}
