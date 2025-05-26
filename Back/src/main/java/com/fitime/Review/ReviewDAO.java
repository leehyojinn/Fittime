package com.fitime.Review;

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

    List<ReviewDTO> listReview(int offset);

	int totalPage();

	int updateReview(ReviewDTO dto);

	int overayReview(ReviewDTO dto);

	void reviewImgSave(int idx, String filename);

	List<String> reviewImgSearch(int idx);

	int fileDelReview(int idx);

	List<Map<String, String>> reviewByUser(Map<String, String> param);

}
