package com.fitime.schedule;

import java.util.List;

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

}
