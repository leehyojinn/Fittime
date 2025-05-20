package com.fitime.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.ScheduleDTO;

@RestController
@CrossOrigin
public class ScheduleController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired
	ScheduleService service;
	
	// 개인일정 등록
	@PostMapping("/schedule_insert")
	public Map<String, Object> schedule_insert(@RequestBody ScheduleDTO dto){
		
		result = new HashMap<String, Object>();
		
		boolean success = service.schedule_insert(dto);
		
		result.put("success", success);
		
		return result;
	}
	
	// 개인일정 리스트
	@PostMapping("/schedule_list/{user_id}")
	public Map<String, Object> schedule_list(@PathVariable String user_id){
		
		result = new HashMap<String, Object>();
		
		List<ScheduleDTO> list = service.schedule_list(user_id);
		
		result.put("list",list);
		
		return result;
	}
	
	//개인일정 업데이트
	@PostMapping("/schedule_update/{user_id}/{schedule_idx}")
	public Map<String, Object> schedule_update(@PathVariable String user_id,@PathVariable Integer schedule_idx, @RequestBody ScheduleDTO dto){
		
		result = new HashMap<String, Object>();
		
		boolean success = service.schedule_update(user_id,schedule_idx,dto);
		
		result.put("success", success);
		
		return result;
	}

	//개인일정 삭제
	@PostMapping("/schedule_del/{user_id}/{schedule_idx}")
	public Map<String, Object> schedule_del(@PathVariable String user_id, @PathVariable Integer schedule_idx){
		
		result = new HashMap<String, Object>();
		
		boolean success = service.schedule_del(user_id,schedule_idx);
		
		result.put("success", success);
		
		return result;
	}
}
