package com.fitime.research;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RestController
public class ResearchController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ResearchService service;
	Map<String, Object>result = null;
	
	@PostMapping(value="/search/location")
	public Map<String, Object>searchLocation(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		List<Map<String, Object>>list = service.searchLocation(param);
		result.put("list", list);
		return result;
	}
	
	@PostMapping(value="/search/trainer")
	public Map<String, Object>searchTrainer(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		List<Map<String, Object>>list = service.searchTrainer(param);
		result.put("list", list);
		return result;
	}
	
	@PostMapping(value = "/search/name")
	public Map<String, Object>searchName(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		return service.searchName(param);
	}
	
	@PostMapping(value="/get/city")
	public Map<String, Object>getCity(){
		return service.getCity();
	}
	
	@PostMapping(value="/get/district")
	public Map<String, Object>getDistrict(@RequestBody Map<String, Object>param){
		return service.getDistrict(param);
	}
	
	@PostMapping(value="/get/neighborhood")
	public Map<String, Object>getNeighborhood(@RequestBody Map<String, Object>param){
		return service.getNeighborhood(param);
	}
	
}
