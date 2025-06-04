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

	public List<Map<String, Object>> user_reservation_schedule(String user_id) {
		return dao.user_reservation_schedule(user_id);
	}
	
	public List<Map<String, Object>> trainer_reservation_schedule(String trainer_id) {
		return dao.trainer_reservation_schedule(trainer_id);
	}
	
	public List<ScheduleDTO> get_center_dayoff(String trainer_id) {
		return dao.get_center_dayoff(trainer_id);
	}

	public List<Map<String, Object>> get_class_schedule(Map<String, Object> param) {
		List<Map<String, Object>> rawList = dao.get_class_schedule(param);
		logger.info("rawList : "+rawList);
		List<Map<String, Object>> result = new ArrayList<>();
		
		for (Map<String, Object> item : rawList) {
			String weekStr = (String) item.get("week");
			List<DayOfWeek> weekDays = parseWeekString(weekStr);
			
			String dateStr = (String) item.get("reservation_date"); // 문자열 형태의 날짜
			
			LocalDate startDate = null;
			try { // 날짜 파싱: yyyy-MM-dd 또는 yy-MM-dd 형식 둘 다 대응
				startDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}catch (DateTimeParseException e1) {
				try {
					startDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yy-MM-dd"));
				}catch (DateTimeParseException e2) {
					System.err.println("날짜 파싱 실패 : "+dateStr);
					continue;					
				}
			}
			
			Object durationObj = item.get("duration");
			int duration = (durationObj != null) ? ((Number) durationObj).intValue() : 0; // duration 이 null 이 나오면 0 으로 반환
			List<LocalDate> scheduleDates = generateScheduleDates(startDate, weekDays, duration); // 날짜 계산
			
			for (LocalDate date : scheduleDates) {
				Map<String, Object> schedule = new HashMap<>();
				schedule.put("title", item.get("product_name"));
				schedule.put("trainer", item.get("trainer_name"));
				schedule.put("user_name", item.get("user_name"));
				schedule.put("date", date.toString());
				schedule.put("start_time", item.get("start_time"));
				schedule.put("end_time", item.get("end_time"));
				result.add(schedule);
			}
		}
		return result;
	}

	public List<LocalDate> generateScheduleDates(LocalDate startDate, List<DayOfWeek> weekDays, int duration) {
		List<LocalDate> scheduleDates = new ArrayList<>();
		LocalDate endDate = startDate.plusDays(duration);
		
		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
			if (weekDays.contains(date.getDayOfWeek())) {
				scheduleDates.add(date);
			}
		}
		return scheduleDates;
	}

	public List<DayOfWeek> parseWeekString(String weekStr) {
		Map<String, DayOfWeek> map = Map.of(
				"월", DayOfWeek.MONDAY,
				"화", DayOfWeek.TUESDAY,
				"수", DayOfWeek.WEDNESDAY,
				"목", DayOfWeek.THURSDAY,
				"금", DayOfWeek.FRIDAY,
				"토", DayOfWeek.SATURDAY,
				"일", DayOfWeek.SUNDAY
				);
		return Arrays.stream(weekStr.split(",")).map(String::trim).map(map::get).collect(Collectors.toList());
	}
}
