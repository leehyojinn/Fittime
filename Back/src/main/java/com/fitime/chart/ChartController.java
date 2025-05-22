package com.fitime.chart;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ChartController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ChartService service;
	Map<String, Object> result = null;
	
	// 센터 기준 통계
	
	@PostMapping(value="/list/chart")
	public Map<String, Object> chart(@RequestBody Map<String, Object> param){
		logger.info("가져오는 파라메터 : {}",param);
		String center_id = (String) param.get("center_id");
		int center_idx = (int) param.get("center_idx");
		return service.chart(center_id, center_idx);
	}

}
