package com.fitime.research;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitime.dto.CenterProfileDTO;

@Service
public class ResearchService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ResearchDAO dao;
	
	public List<Map<String, Object>> searchLocation(Map<String, Object> param) {
		return dao.searchLocation(param);
	}
	
	public List<Map<String, Object>> searchTrainer(Map<String, Object> param) {
		return dao.searchTrainer(param);
	}
	
}
