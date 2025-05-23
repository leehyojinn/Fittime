package com.fitime.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.Profile_fileDTO;
import com.fitime.dto.ReservationDTO;

@Mapper
public interface ReservationDAO {

	int booking(Map<String, Object> param);
	
	int countReservation(int product_idx);
	
	int maxPeople(int product_idx);

	int pages(int pageSize);

	ArrayList<ReservationDTO> listUserBooking(Map<String, Object> param);
	
	ArrayList<ReservationDTO> listTrainerBooking(Map<String, Object> param);

	ArrayList<ReservationDTO> listCenterBooking(Map<String, Object> param);

	ReservationDTO detailBooking(Map<String, Object> param);

	List<Profile_fileDTO> trainerImage(Map<String, Object> param);
	
	int updateBooking(Map<String, Object> param);

	int cancelBooking(Map<String, Object> param);








}
