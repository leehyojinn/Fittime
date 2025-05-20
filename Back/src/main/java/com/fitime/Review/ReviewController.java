package com.fitime.Review;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.ReviewDTO;

@CrossOrigin
@RestController
public class ReviewController {

	Logger logger = LoggerFactory.getLogger(getClass());

	Map<String, Object> result = null;

	@Autowired ReviewService service;

	// list
	@PostMapping(value = "/list/review/{page}")
	public Map<String, Object> listReview(@PathVariable String page, @RequestBody Map<String, Object> params) {

		result = new HashMap<String, Object>();

		Map<String, Object> map = service.listReview(page, params);

		result.put("page", page);
		result.put("params", params);

		return map;
	}

	// detail
	@PostMapping(value = "/detail/review/{idx}")
	public Map<String, Object> detailReview(@RequestBody ReviewDTO dto) {
		logger.info("detail dto : {} ", dto);
		logger.info("review_idx : {}", dto.getReview_idx());
		logger.info("Reservation_idx : {}", dto.getReservation_idx());

		result = new HashMap<String, Object>();

		ReviewDTO ReviewDTO = service.detailReview(dto);
		result.put("dto", ReviewDTO);

		return result;
	}

	// insert
	@PostMapping(value = "/insert/review")
	public Map<String, Object> insertReview(@RequestBody ReviewDTO dto) {
		logger.info("insert dto : {}", dto);

		int reviewCount = service.overayReview(dto);
		
		result = new HashMap<String, Object>();
		boolean success = false; 
		
		if (reviewCount > 0) {
			result.put("success", success);
		}else {
			success = service.insertReview(dto);
			result.put("success", success);			
		}
		
		return result;
		
	}

	// delete
	@PostMapping(value = "del/review/{idx}")
	public Map<String, Object> delReview(@RequestBody ReviewDTO dto) {
		logger.info("delete dto : {} ", dto);

		result = new HashMap<String, Object>();

		boolean success = service.delReview(dto);

		result.put("idx", dto.getReview_idx());
		result.put("success", success);

		return result;
	}

	// update
	@PostMapping(value = "/update/review/{review_idx}")
	public Map<String, Object> updateReview(@RequestBody ReviewDTO dto) {
		logger.info("update dto : {} ", dto);
		logger.info("update list idx : ", dto.getReview_idx());

		result = new HashMap<String, Object>();
		
			result.put("update idx", dto.getReview_idx());
			result.put("update dto", dto);	


		return result;
	}

}
