package com.fitime.schedule;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitime.dto.ScheduleDTO;

@Service
public class ScheduleService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ScheduleDAO dao;

	public boolean schedule_insert(ScheduleDTO dto) {
		int row = dao.schedule_insert(dto);
		return row > 0;
	}

	public List<ScheduleDTO> schedule_list(String user_id) {
		return dao.schedule_list(user_id);
	}

	public boolean schedule_update(String user_id, Integer schedule_idx, ScheduleDTO dto) {
		int row = dao.schedule_update(user_id,schedule_idx,dto);
		return row > 0;
	}

	public boolean schedule_del(String user_id, Integer schedule_idx) {
		int row = dao.schedule_del(user_id,schedule_idx);
		return row > 0;
	}

	public List<Map<String, Object>> user_reservation_schedule(Map<String, Object> param) {
		return dao.user_reservation_schedule(param);
	}
	
	public List<Map<String, Object>> trainer_reservation_schedule(Map<String, Object> param) {
		return dao.trainer_reservation_schedule(param);
	}
	
	public List<ScheduleDTO> get_center_dayoff(String trainer_id) {
		return dao.get_center_dayoff(trainer_id);
	}

	public List<Map<String, Object>> user_remaining_count(String user_id) {
		return dao.user_remaining_count(user_id);
	}

}
