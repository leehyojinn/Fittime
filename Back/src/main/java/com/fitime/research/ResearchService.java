package com.fitime.research;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Map<String, Object> searchName(Map<String, Object> param) {
		Map<String, Object>map = new HashMap<String, Object>();
		int page = ((int)param.get("page")-1)*5;
		String name = (String)param.get("name");
		List<Map<String, Object>> result = dao.trainerName(name,page);
		map.put("trainer_list", result);
		result = dao.centerName(name,page);
		map.put("center_list", result);
		return map;
	}

	public Map<String, Object> getCity() {
		Map<String, Object>map = new HashMap<String, Object>();
		List<String>list = dao.cityList();
		map.put("City", list);
		return map;
	}

	public Map<String, Object> getDistrict(Map<String, Object> param) {
		Map<String, Object>map = new HashMap<String, Object>();
		List<String>list = dao.districtList(param);
		map.put("District", list);
		return map;
	}

	public Map<String, Object> getNeighborhood(Map<String, Object> param) {
		Map<String, Object>map = new HashMap<String, Object>();
		List<String>list = dao.neighborhoodList(param);
		map.put("Neighborhood", list);
		return map;
	}


	
}
