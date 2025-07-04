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

	List<Map<String, Object>> reviewByUser(Map<String, Object> param);


	List<Map<String, Object>> reviewByTrainer(Map<String, Object> param);

	List<Map<String, Object>> reviewByCenter(Map<String, Object> param);

	Map<String, String> getImg(int file_idx);

	Map<String, Object> getReview(int idx);

	int findFiles(int review_idx);

	List<Map<String, Object>> getPhotos(int review_idx);

	int totalPageByUser(Map<String, Object> copyParam);

	int totalPageByTrainer(Map<String, Object> copyParam);

	int totalPageByCenter(Map<String, Object> copyParam);


}
