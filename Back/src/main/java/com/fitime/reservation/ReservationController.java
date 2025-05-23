package com.fitime.reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.Profile_fileDTO;
import com.fitime.dto.ReservationDTO;

@CrossOrigin
@RestController
public class ReservationController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ReservationService service;
	Map<String, Object> result = null;

	// 예약
	@PostMapping(value = "/booking")
	public Map<String, Object> booking(@RequestBody Map<String, Object> param) {
		logger.info("예약 정보 : " + param);
		result = new HashMap<String, Object>();
		boolean success = service.booking(param);
		result.put("success", success);
		return result;
	}

	// 회원용 예약 리스트 조회
	@PostMapping(value = "/list/userBook")
	public Map<String, Object> listUserBooking(@RequestParam(defaultValue = "1") String page,
			@RequestBody Map<String, Object> param) {
		logger.info("회원용 예약 페이지 : {}", page);
		logger.info("사용자 아이디 : {}",param.get("user_id"));
		String user_id = (String) param.get("user_id");
		return service.listUserBooking(page,user_id);
	}

	 // 트레이너용 예약 리스트 조회
	 @PostMapping(value = "/list/trainerBook")
	 public Map<String, Object> listTrainerBooking(@RequestParam(defaultValue = "1") String page,
				@RequestBody Map<String, Object> param) {
		logger.info("트레이너용 예약 페이지 : {}",page);
		logger.info("트레이너 아이디 : {}",param.get("trainer_id"));
		String trainer_id = (String) param.get("trainer_id");
		return service.listTrainerBooking(page,trainer_id);
	 }
	 
	// 센터용 예약 리스트 조회
		 @PostMapping(value = "/list/centerBook")
		 public Map<String, Object> listCenterBooking(@RequestParam(defaultValue = "1") String page,
					@RequestBody Map<String, Object> param) {
			logger.info("센터용 예약 페이지 : {}",page);
			logger.info("센터 아이디 : {}",param.get("center_id"));
			String center_id = (String) param.get("center_id");
			return service.listCenterBooking(page,center_id);
		 }

	// 예약 상세 내역 조회
	@PostMapping(value = "/detail/book")
	public Map<String, Object> detailBooking(@RequestBody Map<String, Object> param) {
		logger.info("예약 상세 보기 : {}", param);
		result = new HashMap<String, Object>();
		ReservationDTO dto = service.detailBooking(param);
		List<Profile_fileDTO> image = service.trainerImage(param);
		result.put("post", dto);
		result.put("image", image);
		return result;
	}
	
	// 예약 수정
	@PostMapping(value = "/update/book")
	public Map<String, Object> updateBooking(@RequestBody Map<String, Object> param) {
		logger.info("예약 수정 내용 : {}", param);
		result = new HashMap<String, Object>();
		boolean success = service.updateBooking(param);
		result.put("success", success);
		return result;
	}

	// 예약 취소
	@PostMapping(value = "/del/book")
	public Map<String, Object> cancelBooking(@RequestBody Map<String, Object> param) {
		logger.info("예약 취소 : {}", param);
		result = new HashMap<String, Object>();
		boolean success = service.cancelBooking(param);
		result.put("success", success);
		return result;
	}

}
