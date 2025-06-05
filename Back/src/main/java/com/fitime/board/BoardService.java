package com.fitime.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fitime.dto.BoardDTO;
import com.fitime.dto.FileImageDTO;
import com.fitime.dto.PageRequestDTO;
import com.fitime.dto.PageResponseDTO;

@Service
public class BoardService {

    @Autowired
    private BoardDAO dao;

    public List<BoardDTO> getBoardListByCategory(String category) {
        return dao.selectByCategory(category); // ✅ 여기서 호출할 메서드도 일치
    }

    public BoardDTO getBoardDetail(int boardIdx) {
        return dao.selectDetail(boardIdx);
    }

    public void insertBoard(BoardDTO boardDTO) {
    	dao.insertBoard(boardDTO);
    }

    public void updateBoard(BoardDTO boardDTO) {
    	dao.updateBoard(boardDTO);
    }

    public void deleteBoard(int boardIdx) {
    	dao.deleteBoard(boardIdx);
    }

	public void write(BoardDTO boardDTO, MultipartFile[] files) {
		dao.write(boardDTO);
		
	}

	public List<String> getFileNameByBoardIdx(int boardIdx) {
		return dao.getFileNameByBoardIdx(boardIdx);
	}

	public PageResponseDTO<BoardDTO> getPagedList(PageRequestDTO pageRequest) {
		return dao.getPagedList(pageRequest);
	}

	public List<BoardDTO> search(String string) {
		return dao.search(string);
	}

	public void softDelete(Integer boardIdx) {
		dao.softDelete(boardIdx);
		
	}

	public BoardDTO read(Integer boardIdx) {
		return dao.read(boardIdx);
	}

	public void update(BoardDTO boardDTO) {
		dao.update(boardDTO);
		
	}

	public void writeWithFiles(BoardDTO boardDTO, MultipartFile[] files) {
	    dao.insertBoard(boardDTO);

	    if (files != null && files.length > 0) {
	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                String fileName = file.getOriginalFilename();

	                // TODO: 파일 저장 처리 (예: 서버에 업로드)
	                // 예: file.transferTo(new File("경로/" + fileName));

	                FileImageDTO fileDTO = new FileImageDTO();
	                fileDTO.setFileName(fileName);
	                fileDTO.setCategory(boardDTO.getCategory());
	                fileDTO.setBoardIdx(boardDTO.getBoardIdx());

	                dao.insertFile(fileDTO); // ✅ 여기에서 500 오류가 났던 부분
	            }
	        }
	    }
	}
}
