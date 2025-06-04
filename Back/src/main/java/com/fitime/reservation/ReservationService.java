package com.fitime.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.fitime.dto.CenterRatingDTO;
import com.fitime.dto.ClassDTO;
import com.fitime.dto.ProductDTO;
import com.fitime.dto.Profile_fileDTO;
import com.fitime.dto.ReservationDTO;
import com.fitime.dto.ScheduleDTO;
import com.fitime.dto.TrainerRatingDTO;

@Service
public class ReservationService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ReservationDAO dao;
	int pageSize = 0;
	
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
	public boolean booking(Map<String, Object> param) {
	    Integer class_idx = (Integer) param.get("class_idx");
	    Integer product_idx = (Integer) param.get("product_idx");
	    String date = (String) param.get("date");
	    String start_time = (String) param.get("start_time");
	    String end_time = (String) param.get("end_time");
	    int row = 0;

	    Integer max_people = dao.maxPeople(product_idx);

	    if (class_idx != null && max_people != null) {
	        // 시간 있는 상품
	        if (start_time != null && end_time != null && !start_time.isEmpty() && !end_time.isEmpty()) {
	            int reservation_cnt = dao.countReservationByTime(class_idx, date, start_time, end_time);
	            if (reservation_cnt < max_people) {
	                row = dao.booking(param);
	            } else {
	                return false;
	            }
	        } else {
	            // 시간 없는 상품: 날짜별로만 인원 체크 (혹은 전체 상품별)
	            int reservation_cnt = dao.countReservationByDate(class_idx, date);
	            if (reservation_cnt < max_people) {
	                row = dao.booking(param);
	            } else {
	                return false;
	            }
	        }
	    } else {
	        // class_idx가 없으면 인원 체크 없이 예약
	        row = dao.booking(param);
	    }
	    return row > 0;
	}



	public Map<String, Object> listUserBooking(String page, String user_id) {
		pageSize = 5;
		int totalPage = dao.pagesByUser(pageSize,user_id);
		int paging = Integer.parseInt(page);
		Map<String, Object> list = new HashMap<String, Object>();
		
		if(totalPage>=paging) {
			int offset = (paging-1)*pageSize;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("user_id", user_id);
			params.put("pageSize", pageSize);
			params.put("offset", offset);
			
			ArrayList<ReservationDTO> bookingList = dao.listUserBooking(params);
			list.put("bookingList", bookingList);
			list.put("totalPage", totalPage);
			list.put("page", paging);
		}
		return list;
	}
	
	public Map<String, Object> listTrainerBooking(String page, String trainer_id) {
		pageSize = 5;
		int totalPage = dao.pagesByTrainer(pageSize,trainer_id);
		int paging = Integer.parseInt(page);
		Map<String, Object> list = new HashMap<String, Object>();
		
		if(totalPage>=paging) {
			int offset = (paging-1)*pageSize;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("trainer_id", trainer_id);
			params.put("pageSize", pageSize);
			params.put("offset", offset);
			
			ArrayList<ReservationDTO> bookingList = dao.listTrainerBooking(params);
			list.put("bookingList", bookingList);
			list.put("totalPage", totalPage);
			list.put("page", paging);
		}
		return list;
	}
	
	public Map<String, Object> listCenterBooking(String page,String center_id) {
		pageSize = 20;
		int totalPage = dao.pagesByCenter(pageSize,center_id);
		int paging = Integer.parseInt(page);
		Map<String, Object> list = new HashMap<String, Object>();
		
		if(totalPage>=paging) {
			int offset = (paging-1)*pageSize;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("center_id", center_id);
			params.put("pageSize", pageSize);
			params.put("offset", offset);
			
			ArrayList<ReservationDTO> bookingList = dao.listCenterBooking(params);
			list.put("bookingList", bookingList);
			list.put("totalPage", totalPage);
			list.put("page", paging);
		}
		return list;
	}
	
	public ReservationDTO detailBooking(Map<String, Object> param) {
		return dao.detailBooking(param);
	}
	
	public List<Profile_fileDTO> trainerImage(Map<String, Object> param) {
		return dao.trainerImage(param);
	}
	
	public boolean updateBooking(Map<String, Object> param) {
		int row = dao.updateBooking(param);
		return row>0;
	}

	public boolean cancelBooking(Map<String, Object> param) {
		int row = dao.cancelBooking(param);
		return row>0;
	}


	public List<CenterRatingDTO> reser_center_info(String center_id) {
		return dao.reser_center_info(center_id);
	}


	public List<ProductDTO> reser_center_product(String center_id) {
		return dao.reser_center_product(center_id);
	}


	public List<TrainerRatingDTO> reser_trainer_info(String center_idx) {
		return dao.reser_trainer_info(center_idx);
	}


	public List<ScheduleDTO> reser_schedule_info(Map<String, Object> param) {
		return  dao.reser_schedule_info(param);
	}


	public List<ClassDTO> reser_class_info(Map<String, String> param) {
		return dao.reser_class_info(param);
	}

    public int countReservationByTime(Integer class_idx, String date, String start_time, String end_time) {
        return dao.countReservationByTime(class_idx, date, start_time, end_time);
    }

    // 날짜별 예약 인원 (시간 없는 상품)
    public int countReservationByDate(Integer product_idx, String date) {
        return dao.countReservationByDate(product_idx, date);
    }

}
