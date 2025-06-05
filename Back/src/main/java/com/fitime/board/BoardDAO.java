package com.fitime.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.BoardDTO;
import com.fitime.dto.FileImageDTO;
import com.fitime.dto.PageRequestDTO;
import com.fitime.dto.PageResponseDTO;

@Mapper
public interface BoardDAO {
    List<BoardDTO> selectByCategory(String category); // 🔧 이 부분 추가
    BoardDTO selectDetail(int boardIdx);
    void insertBoard(BoardDTO boardDTO);
    void updateBoard(BoardDTO boardDTO);
    void deleteBoard(int boardIdx);
	void write(BoardDTO boardDTO);
	void softDelete(Integer boardIdx);
	List<String> getFileNameByBoardIdx(int boardIdx);
	PageResponseDTO<BoardDTO> getPagedList(PageRequestDTO pageRequest);
	List<BoardDTO> search(String string);
	BoardDTO read(int boardIdx);
	void update(BoardDTO boardDTO);
	void writeWithFiles(BoardDTO dto);
	void insertFile(FileImageDTO imageDTO);
}