package com.fitime.research;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
}
