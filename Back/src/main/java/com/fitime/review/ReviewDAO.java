package com.fitime.review;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fitime.dto.ReviewDTO;

@Mapper
public interface ReviewDAO {



	int insertReview(@Param("dto") ReviewDTO dto);

	int delReview(int review_idx);

	ReviewDTO detailReview(ReviewDTO dto);

	int pages();

    List<Map<String, Object>> listReview(int offset);

	int totalPage();

	int updateReview(ReviewDTO dto);

	int overayReview(ReviewDTO dto);

	void reviewImgSave(int idx, String filename);

	List<String> reviewImgSearch(int idx);

	int fileDelReview(int idx);

	List<Map<String, String>> reviewByUser(Map<String, String> param);


	List<Map<String, Object>> reviewByTrainer(Map<String, String> param);

	List<Map<String, Object>> reviewByCenter(Map<String, Object> param);

	Map<String, String> getImg(int file_idx);

	Map<String, Object> getReview(int idx);


}
