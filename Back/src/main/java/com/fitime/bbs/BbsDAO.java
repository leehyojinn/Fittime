package com.fitime.bbs;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.BbsDTO;

@Mapper
public interface BbsDAO {

	int writeBbs(BbsDTO dto);

	void ImgSave(String filename, int board_idx);

	List<String> ImgSearch(int idx);

	int ImgDel(int idx);

	int updateBbs(BbsDTO dto);

	int findFiles(int board_idx);

	int delBbs(int board_idx);

	List<Map<String, Object>> listBbs(String category, int cnt, int offset);

	int getTotalPage(int cnt, String category);

	BbsDTO detailBbs(int idx);

	List<Map<String, Object>> findphotos(int idx);
	
	Map<String, String> getImg(int file_idx);

	 
}
