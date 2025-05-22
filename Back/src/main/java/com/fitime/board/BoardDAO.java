package com.fitime.board;

import com.fitime.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardDAO {

    // 게시글 전체 목록
    List<BoardDTO> selectBoardList();

    // 게시글 상세 조회
    BoardDTO selectBoardById(int boardIdx);

    // 게시글 등록 (PK 수동 지정 시)
    void insertBoard(BoardDTO dto);

    // 게시글 등록 (PK 자동 증가 시)
    void boardInsert(BoardDTO dto);

    // 게시글 수정
    void updateBoard(BoardDTO dto);

    // 게시글 삭제
    void deleteBoard(int boardIdx);

    // 전체 게시글 개수 (페이징용)
    int getTotalCount();

    // 페이징 처리된 게시글 목록
    List<BoardDTO> listWithPaging(@Param("offset") int offset, @Param("size") int size);

    // 첨부 파일 등록
    void insertFile(@Param("boardIdx") int boardIdx, @Param("fileName") String fileName);

    // 첨부 파일명 조회
    List<String> selectFileNames(int boardIdx);

    // 게시글 검색 (제목/내용 기준 등 자유롭게 처리)
    List<BoardDTO> searchBoard(String keyword);

	void softDeleteBoard(int boardIdx);

	void deleteBoardImage(int boardIdx);
}
