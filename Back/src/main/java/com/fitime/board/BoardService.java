package com.fitime.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitime.dto.BoardDTO;
import com.fitime.dto.PageRequestDTO;
import com.fitime.dto.PageResponseDTO;

@Service
public class BoardService {

    @Autowired
    private BoardDAO dao;

    public List<BoardDTO> list() {
        return dao.selectBoardList();
    }

    public BoardDTO read(int boardIdx) {
        return dao.selectBoardById(boardIdx);
    }

    public void write(BoardDTO dto) {
        // boardIdx가 직접 지정되면 insertBoard, 자동이면 boardInsert 사용
        if (dto.getBoardIdx() > 0) {
            dao.insertBoard(dto); // 수동 boardIdx 입력
        } else {
            dao.boardInsert(dto); // board_idx는 auto_increment
        }
    }

    public void modify(BoardDTO dto) {
        dao.updateBoard(dto);
    }

    public void remove(int boardIdx) {
        dao.deleteBoard(boardIdx);
    }

    public PageResponseDTO<BoardDTO> getPagedList(PageRequestDTO pageRequest) {
        int offset = (int) pageRequest.getOffset(); // (page - 1) * size
        int size = pageRequest.getSize();

        List<BoardDTO> list = dao.listWithPaging(offset, size);
        int total = dao.getTotalCount();

        return new PageResponseDTO<>(list, total, size, pageRequest.getPage());
    }

	public void saveFile(int boardIdx, String fileName) {
		dao.insertFile(boardIdx, fileName);
		
	}

	public List<String> getFileNameByBoardIdx(int boardIdx) {
		return dao.selectFileNames(boardIdx);
	}

	public List<BoardDTO> search(String keyword) {
		return dao.searchBoard(keyword);
	}

	public void softDelete(int boardIdx) {
		dao.softDeleteBoard(boardIdx);
		
	}

	
}
