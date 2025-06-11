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

	List<String> cityList();

	List<String> districtList(Map<String, Object> param);

	List<String> neighborhoodList(Map<String, Object> param);

	int trainerTotalPage(String name, int page);

	int centerTotalPage(String name, int page);


}
