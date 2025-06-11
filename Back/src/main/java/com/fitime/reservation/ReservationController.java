package com.fitime.reservation;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.CenterRatingDTO;
import com.fitime.dto.ClassDTO;
import com.fitime.dto.ProductDTO;
import com.fitime.dto.Profile_fileDTO;
import com.fitime.dto.ReservationDTO;
import com.fitime.dto.ScheduleDTO;
import com.fitime.dto.TrainerRatingDTO;

@CrossOrigin(
	    origins = "http://192.168.0.114:3000",
	    allowedHeaders = "*",
	    allowCredentials = "true"
	)
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

	//센터 정보
	@PostMapping("/reservation/center_info/{center_id}")
	public Map<String, Object> reser_center_info(@PathVariable String center_id){
		result = new HashMap<String, Object>();
		
		List<CenterRatingDTO> list = service.reser_center_info(center_id);
		
		result.put("list", list);
		
		return result;
	}
	
	//상품정보
	@PostMapping("/reservation/center_product/{center_id}")
	public Map<String, Object> reser_center_product(@PathVariable String center_id){
		
		result = new HashMap<String, Object>();
		
		List<ProductDTO> list = service.reser_center_product(center_id);
		
		result.put("list", list);
		
		return result;
	}
	
	//트레이너 정보
	@PostMapping("/reservation/trainer_info/{center_idx}")
	public Map<String, Object> reser_trainer_info(@PathVariable String center_idx){
		
		result = new HashMap<String, Object>();
		
		List<TrainerRatingDTO> list = service.reser_trainer_info(center_idx);
		
		result.put("list", list);
		
		return result;
	}
	
	//스케줄 정보
	@PostMapping("/reservation/schedule_info")
	public Map<String, Object> reser_schedule_info(@RequestBody Map<String, Object> param) {
	    Map<String, Object> result = new HashMap<>();
	    List<ScheduleDTO> list = service.reser_schedule_info(param);
	    result.put("list", list);
	    return result;
	}
	
	//클래스 정보
	@PostMapping("/reservation/class_info")
	public Map<String, Object> reser_class_info(@RequestBody Map<String, String> param){
		
		result = new HashMap<String, Object>();
		
		List<ClassDTO> list = service.reser_class_info(param);
		
		result.put("list", list);
		
		return result;
	}
	
	@PostMapping("/reservation/booked_count")
    public Map<String, Object> bookedCount(@RequestBody Map<String, Object> param) {
        List<Map<String, Object>> times = (List<Map<String, Object>>) param.get("times");
        Integer class_idx = (Integer) param.get("class_idx");
        String date = (String) param.get("date");

        Map<String, Integer> counts = new HashMap<>();
        for (Map<String, Object> t : times) {
            String start_time = (String) t.get("start_time");
            String end_time = (String) t.get("end_time");
            int cnt = service.countReservationByTime(class_idx, date, start_time, end_time);
            counts.put(start_time + "-" + end_time, cnt);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("counts", counts);
        return result;
    }

    @PostMapping("/reservation/booked_count_date")
    public Map<String, Object> bookedCountDate(@RequestBody Map<String, Object> param) {
        Integer product_idx = (Integer) param.get("product_idx");
        String date = (String) param.get("date");
        int cnt = service.countReservationByDate(product_idx, date);
        Map<String, Object> result = new HashMap<>();
        result.put("count", cnt);
        return result;
    }
	
    @PostMapping("/reservation/booked_count_date_range")
    public Map<String, Object> bookedCountDateRange(@RequestBody Map<String, Object> param) {
        Integer product_idx = (Integer) param.get("product_idx");
        String start_date = (String) param.get("start_date");
        String end_date = (String) param.get("end_date");
        Map<String, Integer> counts = service.countReservationByDateRange(product_idx, start_date, end_date);
        Map<String, Object> result = new HashMap<>();
        result.put("counts", counts);
        return result;
    }
    
    @PostMapping("/reservation/myproduct_list")
    public Map<String, Object> myproduct_list(@RequestBody Map<String, Object> param){
        result = new HashMap<String, Object>();
        List<Map<String, Object>> list = service.myproduct_list(param);
        result.put("list", list);
        return result;
    }

    
}
