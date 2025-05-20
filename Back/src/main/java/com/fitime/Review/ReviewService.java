package com.fitime.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitime.dto.ReviewDTO;

@Service
public class ReviewService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired ReviewDAO dao;

	public Map<String, Object> listReview(String page, Map<String, Object> params) {
		
		int totalPage = dao.pages();
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		int listPage = Integer.parseInt(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ( totalPage >= listPage ) {
			int offset = (listPage-1) * 10 ;
			list = dao.listReview(offset);
			
			map.put("list", list);
			map.put("pages", totalPage);
			map.put("page", page);
			map.put("total", dao.totalPage());
		}
		
		return map;
	}

	public boolean insertReview(ReviewDTO dto) {
		int row = dao.insertReview(dto);
		
		return row > 0 ? true : false;
	}

	public boolean delReview(ReviewDTO dto) {
		int row = dao.delReview(dto.getReview_idx());
		return row > 0;
	}

	public ReviewDTO detailReview(ReviewDTO dto) {
		return dao.detailReview(dto);
	}

	public boolean updateReview(ReviewDTO dto) {
		int row = dao.updateReview(dto);		
		return row > 0 ? true : false;
	}

	public int overayReview(ReviewDTO dto) {
		return dao.overayReview(dto);
	}

	


}
