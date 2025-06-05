package com.fitime.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fitime.dto.CenterRatingDTO;
import com.fitime.dto.ClassDTO;
import com.fitime.dto.ProductDTO;
import com.fitime.dto.Profile_fileDTO;
import com.fitime.dto.ReservationDTO;
import com.fitime.dto.ScheduleDTO;
import com.fitime.dto.TrainerRatingDTO;

@Mapper
public interface ReservationDAO {

	int booking(Map<String, Object> param);
	
	int countReservation(int product_idx);
	
	int maxPeople(int product_idx);

	int pagesByUser(int pageSize, String user_id);
	
	int pagesByTrainer(int pageSize, String user_id);
	
	int pagesByCenter(int pageSize, String user_id);

	ArrayList<ReservationDTO> listUserBooking(Map<String, Object> param);
	
	ArrayList<ReservationDTO> listTrainerBooking(Map<String, Object> param);

	ArrayList<ReservationDTO> listCenterBooking(Map<String, Object> param);

	ReservationDTO detailBooking(Map<String, Object> param);

	List<Profile_fileDTO> trainerImage(Map<String, Object> param);
	
	int updateBooking(Map<String, Object> param);

	int cancelBooking(Map<String, Object> param);

	List<CenterRatingDTO> reser_center_info(String center_id);

	List<ProductDTO> reser_center_product(String center_id);

	List<TrainerRatingDTO> reser_trainer_info(String center_idx);

	List<ScheduleDTO> reser_schedule_info(Map<String, Object> param);

	List<ClassDTO> reser_class_info(Map<String, String> param);

	int countReservationByTime(
		    @Param("class_idx") Integer class_idx,
		    @Param("date") String date,
		    @Param("start_time") String start_time,
		    @Param("end_time") String end_time
		);

	int countReservationByDate(
	    @Param("product_idx") Integer product_idx,
	    @Param("date") String date
	);

    List<Map<String, Object>> countReservationByDateRange(
            @Param("product_idx") int product_idx,
            @Param("start_date") String start_date,
            @Param("end_date") String end_date
    );

	List<Map<String, Object>> myproduct_list(String user_id);

	void decrementBuyListCount(Integer buy_idx);
}
