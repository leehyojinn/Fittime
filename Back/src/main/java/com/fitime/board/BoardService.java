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

    // ✅ 게시글 목록 조회
    public List<BoardDTO> list() {
        return dao.selectBoardList();
    }

    // ✅ 게시글 상세 조회 + 이미지 리스트 추가
    public BoardDTO read(int boardIdx) {
        BoardDTO dto = dao.selectBoardById(boardIdx);

        // ✅ 이미지 파일명들을 file_Image 테이블에서 조회해서 세팅
        List<String> imageList = dao.selectFileNames(boardIdx);
        dto.setImageList(imageList);

        return dto;
    }

    // ✅ 게시글 작성 + 이미지 파일명 저장
    public void write(BoardDTO dto, List<String> savedFileNames) {
        if (dto.getBoardIdx() > 0) {
            dao.insertBoard(dto); // 수동 boardIdx 입력 시
        } else {
            dao.boardInsert(dto); // auto_increment
        }

        // ✅ 이미지 파일명 file_Image 테이블에 저장
        if (savedFileNames != null && !savedFileNames.isEmpty()) {
            for (String fileName : savedFileNames) {
                dao.insertFile(dto.getBoardIdx(), fileName);
            }
        }
    }

    // ✅ 파일 없는 단독 게시글 등록용 오버로딩
    public void write(BoardDTO dto) {
        write(dto, null);
    }

    // ✅ 게시글 수정
    public void modify(BoardDTO dto) {
        dao.updateBoard(dto);
    }

    // ✅ 게시글 삭제 시 이미지도 함께 삭제
    public void remove(int boardIdx) {
        dao.deleteBoardImage(boardIdx); // file_Image 테이블 먼저 삭제
        dao.deleteBoard(boardIdx);      // 그 다음 게시글 삭제
    }

    // ✅ 페이징 처리된 게시글 리스트
    public PageResponseDTO<BoardDTO> getPagedList(PageRequestDTO pageRequest) {
        int offset = (int) pageRequest.getOffset();
        int size = pageRequest.getSize();

        List<BoardDTO> list = dao.listWithPaging(offset, size);
        int total = dao.getTotalCount();

        return new PageResponseDTO<>(list, total, size, pageRequest.getPage());
    }

    // ✅ 이미지 저장 개별 호출용 (필요 시)
    public void saveFile(int boardIdx, String fileName) {
        dao.insertFile(boardIdx, fileName);
    }

    // ✅ 이미지 목록 조회 (파일명만)
    public List<String> getFileNameByBoardIdx(int boardIdx) {
        return dao.selectFileNames(boardIdx);
    }

    // ✅ 게시글 검색
    public List<BoardDTO> search(String keyword) {
        return dao.searchBoard(keyword);
    }

    // ✅ 게시글 상태만 비활성 처리
    public void softDelete(int boardIdx) {
        dao.softDeleteBoard(boardIdx);
    }

}
