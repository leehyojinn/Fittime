package com.fitime.chart;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChartDAO {
	
	List<Map<String, Object>> memberChart(String center_id);

	List<Map<String, Object>> productChart(String center_id);
	
	List<Map<String, Object>> currentTrainerChart(int center_idx);
	
	List<Map<String, Object>> currentSalesChart(String center_id);
	
	List<Map<String, Object>> salesChart(String center_id);

	List<Map<String, Object>> bookChart(String center_id);

	List<Map<String, Object>> productSalesChart(String center_id);

	List<Map<String, Object>> trainerBookChart(String center_id);

	List<Map<String, Object>> trainerRatingChart(int center_idx);

	List<Map<String, Object>> productPopularChart(String center_id);

	List<Map<String, Object>> trainerChart(int center_idx);

	int getCenterIdx(String user_id);


}
