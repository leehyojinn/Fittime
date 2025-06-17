package com.fitime.mainpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.ReviewDTO;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class MainPageController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired
	MainPageService service;
	
	@PostMapping("/center_rating/list")
	public Map<String, Object> CenterRatingList(){
		
		result = new HashMap<String, Object>();
		
		List<ReviewDTO> list = service.CenterRatingList();
		
		result.put("list", list);
		
		return result;
	}
	
	@PostMapping("/trainer_rating/list")
	public Map<String, Object> TrainerRatingList(){
		
		result = new HashMap<String, Object>();
		
		List<ReviewDTO> list = service.TrainerRatingList();
		
		result.put("list", list);
		
		return result;
		
	}

}
