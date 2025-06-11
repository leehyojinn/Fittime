package com.fitime.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fitime.dto.BoardDTO;
import com.fitime.dto.PageRequestDTO;
import com.fitime.dto.PageResponseDTO;

@RestController
@RequestMapping("/board")
@CrossOrigin(
	    origins = "http://192.168.0.114:3000",
	    allowedHeaders = "*",
	    allowCredentials = "true"
	)
public class BoardController {

    @Autowired
    private BoardService service;

    // ✅ 게시글 목록 조회
    @PostMapping("/list/board")
    public List<BoardDTO> getBoardList() {
        return service.list();
    }

    // ✅ 게시글 상세 조회 (이미지 포함)
    @PostMapping("/detail/board")
    public BoardDTO getBoardDetail(@RequestBody Map<String, Integer> param) {
        int id = param.get("id");
        return service.read(id);
    }

    // ✅ 게시글 등록 (텍스트 전용)
    @PostMapping("/insert/board")
    public String writeBoard(@RequestBody BoardDTO dto) {
        service.write(dto);
        return "작성 완료";
    }

    // ✅ 게시글 수정
    @PostMapping("/update/board")
    public String updateBoard(@RequestBody BoardDTO dto) {
        service.modify(dto);
        return "수정 완료";
    }

    // ✅ 게시글 삭제 (소프트 삭제 처리)
    @PostMapping("/delete/board")
    public String deleteBoard(@RequestBody(required = false) Map<String, Integer> param) {
        if (param == null || !param.containsKey("boardIdx")) {
            return "삭제 실패: boardIdx가 포함되어 있지 않습니다.";
        }

        Integer boardIdx = param.get("boardIdx");

        if (boardIdx == null) {
            return "삭제 실패: boardIdx 값이 null입니다.";
        }

        service.softDelete(boardIdx);
        return "삭제 완료 (Soft Delete)";
    }

    // ✅ 파일 포함 게시글 작성 (다중 파일 가능)
    @PostMapping("/writeWithFiles/board")
    public String writeWithFiles(
            @RequestParam("userId") String userId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("category") String category,
            @RequestParam(value = "file", required = false) List<MultipartFile> files
    ) {
        BoardDTO board = new BoardDTO();
        board.setUserId(userId);
        board.setTitle(title);
        board.setContent(content);
        board.setCategory(category);

        List<String> savedFileNames = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        String originalName = file.getOriginalFilename();
                        String uuid = UUID.randomUUID().toString();
                        String fileName = uuid + "_" + originalName;

                        File saveFile = new File("C:/upload/", fileName);
                        file.transferTo(saveFile);

                        savedFileNames.add(fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        service.write(board, savedFileNames);
        return "redirect:/somewhere"; // 또는 "ok"
    }

    // ✅ 게시글에 첨부된 파일 목록 조회
    @PostMapping("/fileList/board")
    public List<String> getFileList(@RequestBody Map<String, Integer> param) {
        int boardIdx = param.get("boardIdx");
        return service.getFileNameByBoardIdx(boardIdx);
    }

    // ✅ 게시글 페이징 목록
    @PostMapping("/pagingList/board")
    public PageResponseDTO<BoardDTO> getPagingList(@RequestBody PageRequestDTO pageRequest) {
        return service.getPagedList(pageRequest);
    }

    // ✅ 게시글 검색
    @PostMapping("/search/board")
    public List<BoardDTO> search(@RequestBody Map<String, String> param) {
        String keyword = param.get("keyword");
        return service.search(keyword);
    }
}
