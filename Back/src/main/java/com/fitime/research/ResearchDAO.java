package com.fitime.research;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResearchDAO {

	List<Map<String, Object>> searchLocation(Map<String, Object> param);

	List<Map<String, Object>> searchTrainer(Map<String, Object> param);

	List<Map<String, Object>> trainerName(String name, int page);

	List<Map<String, Object>> centerName(String name, int page);


}
