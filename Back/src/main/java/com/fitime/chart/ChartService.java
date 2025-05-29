package com.fitime.chart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChartService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ChartDAO dao;
	
	public Map<String, Object> chart(String center_id, int center_idx) {
		
		// 누적 회원 수
		List<Map<String, Object>> memberData = dao.memberChart(center_id);
		
		// 총 판매 상품 수
		List<Map<String, Object>> productData = dao.productChart(center_id);
		
		// 현재 트레이너 수
		List<Map<String, Object>> currentTrainerData = dao.currentTrainerChart(center_idx);
		
		// 해당 월 매출
		List<Map<String, Object>> currentSalesData = dao.currentSalesChart(center_id);
		
		// 월별 매출
		List<Map<String, Object>> salesData = dao.salesChart(center_id);
		
		// 월별 예약 수
		List<Map<String, Object>> bookData = dao.bookChart(center_id);
		
		// 월별 상품판매 수
		List<Map<String, Object>> productSalesData = dao.productSalesChart(center_id);
		
		// 월별 트레이너 예약 수
		List<Map<String, Object>> trainerBookData = dao.trainerBookChart(center_id);
		
		// 월별 인기 트레이너 (별점 기준)
		List<Map<String, Object>> trainerRatingData = dao.trainerRatingChart(center_idx);
		
		// 인기 상품 순
		List<Map<String, Object>> productPopularData = dao.productPopularChart(center_id);
		
		// 트레이너 현황
		List<Map<String, Object>> trainerData = dao.trainerChart(center_idx);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("memberData", memberData);
		result.put("productData", productData);
		result.put("currentTrainerData", currentTrainerData);
		result.put("currentSalesData", currentSalesData);
		result.put("salesData", salesData);
		result.put("bookData", bookData);
		result.put("productSalesData", productSalesData);
		result.put("trainerBookData", trainerBookData);
		result.put("trainerRatingData", trainerRatingData);
		result.put("productPopularData", productPopularData);
		result.put("trainerData", trainerData);
		return result;
	}

	public Integer getCenterIdx(String center_id) {
		return dao.getCenterIdx(center_id); // null 허용
	}

}
