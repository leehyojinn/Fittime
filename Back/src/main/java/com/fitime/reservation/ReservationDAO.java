package com.fitime.reservation;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.ReservationDTO;

@Mapper
public interface ReservationDAO {

	int booking(Map<String, Object> param);

	int pages(int pageSize);

	ArrayList<ReservationDTO> listUserBooking(Map<String, Object> params);
	
	ArrayList<ReservationDTO> listTrainerBooking(Map<String, Object> params);

	ArrayList<ReservationDTO> listCenterBooking(Map<String, Object> params);

	ReservationDTO detailBooking(Map<String, Object> param);

	int updateBooking(Map<String, Object> param);

	int cancelBooking(Map<String, Object> param);







}
