package com.fitime.schedule;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fitime.dto.ScheduleDTO;

@Mapper
public interface ScheduleDAO {

	int schedule_insert(ScheduleDTO dto);

	List<ScheduleDTO> schedule_list(String user_id);

	int schedule_update(@Param("user_id") String user_id,@Param("schedule_idx") Integer schedule_idx, @Param("dto") ScheduleDTO dto);

	int schedule_del(String user_id, Integer schedule_idx);
	
	List<Map<String, Object>> user_reservation_schedule(Map<String, Object> param);
	
	List<Map<String, Object>> trainer_reservation_schedule(Map<String, Object> param);

	List<ScheduleDTO> get_center_dayoff(String trainer_id);

	List<Map<String, Object>> get_user_class_schedule(Map<String, Object> param);
	
	List<Map<String, Object>> get_class_schedule(Map<String, Object> param);

	List<Map<String, Object>> user_remaining_count(String user_id);

}