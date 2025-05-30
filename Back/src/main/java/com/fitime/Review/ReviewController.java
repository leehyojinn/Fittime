package com.fitime.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fitime.dto.ReviewDTO;

@CrossOrigin
@RestController
public class ReviewController {

	Logger logger = LoggerFactory.getLogger(getClass());

	Map<String, Object> result = null;

	@Autowired ReviewService service;

	// list
	@PostMapping(value = "/list/review/{page}")
	public Map<String, Object> listReview(@PathVariable String page) {

		return service.listReview(page);
	}

	// detail
	@PostMapping(value = "/detail/review/{idx}")
	public Map<String, Object> detailReview(MultipartFile[] files, @RequestBody ReviewDTO dto) {
		logger.info("detail dto : {} ", dto);
		logger.info("review_idx : {}", dto.getReview_idx());
		logger.info("Reservation_idx : {}", dto.getReservation_idx());
		logger.info("files : {} ", files);
		
		result = new HashMap<String, Object>();

		ReviewDTO ReviewDTO = service.detailReview(dto);
		result.put("dto", ReviewDTO);

		return result;
	}

	// insert
	@PostMapping(value = "/insert/review")
	public Map<String, Object> insertReview(@ModelAttribute ReviewDTO dto) {
		logger.info("insert dto : {}", dto);

		int reviewCount = service.overayReview(dto);
		
		result = new HashMap<String, Object>();
		boolean success = false; 
		
		MultipartFile[] files = dto.getFiles();
		
		if (reviewCount > 0) {
			result.put("success", success);
		}else {
			success = service.insertReview(files,dto);
			result.put("success", success);			
		}
		
		return result;
		
	}

	// delete
	@PostMapping(value = "/del/review/{review_idx}")
	public Map<String, Object> delReview(@PathVariable int review_idx) {
		logger.info("review_idx : "+ review_idx);

		result = new HashMap<String, Object>();

		boolean success = service.delReview(review_idx);

		result.put("idx",review_idx);
		result.put("success", success);

		return result;
	}

	// update
	@PostMapping(value = "/update/review/{review_idx}")
	public Map<String, Object> updateReview(MultipartFile[] files, ReviewDTO dto) {
		logger.info("update dto : {} ", dto);
		logger.info("update list idx : ", dto.getReview_idx());

		result = new HashMap<String, Object>();
		
		boolean success = service.updateReview(files, dto);
		
		result.put("success" , success);
			
		return result;
	}

	@PostMapping(value="/list/reviewByUser")
	public Map<String, Object>reviewByUser(@RequestBody Map<String, String>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		List<Map<String, String>>list = service.reviewByUser(param);
		result.put("reviews", list);
		return result;
	}
	

	@PostMapping(value="/list/reviewByTrainer")
	public Map<String, Object>reviewByTrainer(@RequestBody Map<String, String>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		List<Map<String, Object>>list = service.reviewByTrainer(param);
		result.put("reviews", list);
		return result;
	}
	
	@PostMapping(value="/list/reviewByCenter")
	public Map<String, Object>reviewByCenter(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		List<Map<String, Object>>list = service.reviewByCenter(param);
		result.put("reviews", list);
		return result;
	}

	// 사진 가져오기
	@GetMapping(value="/reviewImg/{file_idx}")
	public ResponseEntity<Resource> img(@PathVariable String file_idx){
		int idx = Integer.parseInt(file_idx);
		return service.getImg(idx);
	}
	
}
