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
@CrossOrigin(
	    origins = "http://192.168.0.114:3000",
	    allowedHeaders = "*",
	    allowCredentials = "true"
	)
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
	
	// 회원용 클래스 일정
		@PostMapping(value="/user_reservation_schedule/{user_id}")
		public Map<String, Object> user_reservation_schedule(@PathVariable String user_id, @RequestBody Map<String, Object> param){
			logger.info("회원용 클래스 일정 불러오기 : user_id={}, param={}", user_id, param);
			Map<String, Object>copyParam = new HashMap<String, Object>(param);
			copyParam.put("user_id", user_id);
			String reservation_date = (String) param.get("reservation_date");
			List<Map<String, Object>> userList = service.user_reservation_schedule(copyParam);
			result = new HashMap<String, Object>();
			result.put("user_id", user_id);
			result.put("reservation_date", reservation_date);
			result.put("userList", userList);
			return result;
		}
		
		// 트레이너용 클래스 일정
		@PostMapping(value="/trainer_reservation_schedule/{trainer_id}")
		public Map<String, Object> trainer_reservation_schedule(@PathVariable String trainer_id, @RequestBody Map<String, Object> param){
			logger.info("트레이너용 클래스 일정 불러오기 : trainer_id={}, param={}", trainer_id, param);
			Map<String, Object>copyParam = new HashMap<String, Object>(param);
			copyParam.put("trainer_id", trainer_id);
			String reservation_date = (String) param.get("reservation_date");
			List<Map<String, Object>> trainerList = service.trainer_reservation_schedule(copyParam);
			result = new HashMap<String, Object>();
			result.put("trainer_id", trainer_id);
			result.put("reservation_date",reservation_date);
			result.put("trainerList", trainerList);
			return result;
		}
	
	// 트레이너 캘린더 센터 휴무 동기화
	@PostMapping(value="/center_dayoff/{trainer_id}")
	public Map<String, Object> get_center_dayoff(@PathVariable String trainer_id){
		logger.info("해당 트레이너 휴무일 : {}",trainer_id);
		result = new HashMap<String, Object>();
		List<ScheduleDTO> dayoff = service.get_center_dayoff(trainer_id);
		result.put("dayoff", dayoff);
		return result;
	}
	
	// 회원 잔여횟수 / 남은 기간 조회
	@PostMapping(value="/user_remaining_count")
	public Map<String, Object> user_remaining_count(@RequestBody Map<String, Object> param){
		String user_id = (String) param.get("user_id");
		int reservation_idx = (int) param.get("reservation_idx");
		List<Map<String, Object>> remainList = service.user_remaining_count(user_id);
		logger.info("잔여횟수/남은기간 : {}",user_id);
		result = new HashMap<String, Object>();
		result.put("user_id", user_id);
		result.put("reservation_idx", reservation_idx);
		result.put("remainList", remainList);
		return result;
	}
	
}
