package com.fitime.reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
		
	    String date = (String) param.get("date");
	    String start_time = (String) param.get("start_time");
	    String end_time = (String) param.get("end_time");
	    int row = 0;
	    
	    String trainer_id = null;
	    Object trainer_id_Obj = param.get("trainer_id");
	    if(trainer_id_Obj instanceof String) {
	    	trainer_id = (String)trainer_id_Obj;
	    }

	    Integer class_idx = null;
	    Object classIdxObj = param.get("class_idx");
	    if (classIdxObj instanceof Number) {
	        class_idx = ((Number) classIdxObj).intValue();
	    } else if (classIdxObj instanceof String) {
	        class_idx = Integer.parseInt((String) classIdxObj);
	    }

	    Integer product_idx = null;
	    Object productIdxObj = param.get("product_idx");
	    if (productIdxObj instanceof Number) {
	        product_idx = ((Number) productIdxObj).intValue();
	    } else if (productIdxObj instanceof String) {
	        product_idx = Integer.parseInt((String) productIdxObj);
	    }

	    Integer max_people = dao.maxPeople(product_idx);

//	    if (class_idx != null && max_people != null) {
//	        // 시간 있는 상품
//	        if (start_time != null && end_time != null && !start_time.isEmpty() && !end_time.isEmpty()) {
//	            int reservation_cnt = dao.countReservationByTime(class_idx, date, start_time, end_time);
//	            if (reservation_cnt < max_people) {
//	                row = dao.booking(param);
//	            } else {
//	                return false;
//	            }
//	        } else {
//	            // 시간 없는 상품: 날짜별로만 인원 체크 (혹은 전체 상품별)
//	            int reservation_cnt = dao.countReservationByDate(product_idx, date);
//	            if (reservation_cnt < max_people) {
//	                row = dao.booking(param);
//	            } else {
//	                return false;
//	            }
//	        }
//	    } else {
//	        // class_idx가 없으면 인원 체크 없이 예약
//	        row = dao.booking(param);
//	    }
	    
	    if (trainer_id != null && max_people != null) {
	        // 트레이너 있는 상품
	        if (start_time != null && end_time != null && !start_time.isEmpty() && !end_time.isEmpty()) {
	            int reservation_cnt = dao.countReservationByTime(class_idx, date, start_time, end_time,null);
	            if (reservation_cnt < max_people) {
	                row = dao.booking(param);
	            } else {
	                return false;
	            }
	        } 
	    } else {
	        // 트레이너가 없는 상품 = 기간 상품 => 인원 체크 없이 예약
	        row = dao.booking(param);
	    }
	    
	    // ★ 구매한 상품(buy_idx가 있을 때) count 차감
	    if (row > 0 && param.get("buy_idx") != null) {
	        Integer buy_idx = null;
	        Object buyIdxObj = param.get("buy_idx");
	        if (buyIdxObj instanceof Number) {
	            buy_idx = ((Number) buyIdxObj).intValue();
	        } else if (buyIdxObj instanceof String) {
	            buy_idx = Integer.parseInt((String) buyIdxObj);
	        }
	        if (buy_idx != null) {
	            dao.decrementBuyListCount(buy_idx);
	        }
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
			
			List<ReservationDTO> bookingList = dao.listUserBooking(params);
			list.put("bookingList", bookingList);
			list.put("totalPage", totalPage);
			list.put("page", paging);
		}
		return list;
	}
	
	public Map<String, Object> listTrainerBooking(String page, String trainer_id) {
		pageSize = 10;
		int totalPage = dao.pagesByTrainer(pageSize,trainer_id);
		int paging = Integer.parseInt(page);
		Map<String, Object> list = new HashMap<String, Object>();
		
		if(totalPage>=paging) {
			int offset = (paging-1)*pageSize;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("trainer_id", trainer_id);
			params.put("pageSize", pageSize);
			params.put("offset", offset);
			
			List<ReservationDTO> bookingList = dao.listTrainerBooking(params);
			list.put("bookingList", bookingList);
			list.put("totalPage", totalPage);
			list.put("page", paging);
		}
		return list;
	}
	
	public Map<String, Object> listCenterBooking(String page,String center_id) {
		pageSize = 10;
		int totalPage = dao.pagesByCenter(pageSize,center_id);
		int paging = Integer.parseInt(page);
		Map<String, Object> list = new HashMap<String, Object>();
		
		if(totalPage>=paging) {
			int offset = (paging-1)*pageSize;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("center_id", center_id);
			params.put("pageSize", pageSize);
			params.put("offset", offset);
			
			List<ReservationDTO> bookingList = dao.listCenterBooking(params);
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

    public int countReservationByTime(Integer class_idx, String date, String start_time, String end_time, Integer product_idx) {
        return dao.countReservationByTime(class_idx, date, start_time, end_time,product_idx);
    }

    // 날짜별 예약 인원 (시간 없는 상품)
    public int countReservationByDate(Integer product_idx, String date) {
        return dao.countReservationByDate(product_idx, date);
    }
    
    public Map<String, Integer> countReservationByDateRange(int product_idx, String start_date, String end_date) {
        List<Map<String, Object>> list = dao.countReservationByDateRange(product_idx, start_date, end_date);
        Map<String, Integer> result = new HashMap<>();
        for (Map<String, Object> row : list) {
            String date = (String) row.get("date");
            Integer cnt = ((Number) row.get("cnt")).intValue();
            result.put(date, cnt);
        }
        return result;
    }



	public List<Map<String, Object>> myproduct_list(Map<String, Object> param) {
		return dao.myproduct_list(param);
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	    public void decrementDailyCount() {
	        dao.decrementDailyCount();
	}



	public int maxPeople(int idx) {
		return dao.maxPeople(idx);
	}

}
