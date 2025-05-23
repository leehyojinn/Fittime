package com.fitime.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.fitime.dto.Profile_fileDTO;
import com.fitime.dto.ReservationDTO;

@Service
public class ReservationService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ReservationDAO dao;
	int pageSize = 0;
	
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
	public boolean booking(Map<String, Object> param) {
		int product_idx = (Integer) param.get("product_idx");
		int reservation_cnt = dao.countReservation(product_idx);
		int max_people = dao.maxPeople(product_idx);
		int row = 0;
		if(reservation_cnt < max_people) {
			row = dao.booking(param);
		}		
		return row>0;
	}

	public Map<String, Object> listUserBooking(String page, String user_id) {
		pageSize = 5;
		int totalPage = dao.pages(pageSize);
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
		int totalPage = dao.pages(pageSize);
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
		int totalPage = dao.pages(pageSize);
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


}
