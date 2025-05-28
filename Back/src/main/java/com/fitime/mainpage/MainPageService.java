package com.fitime.mainpage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitime.dto.ReviewDTO;

@Service
public class MainPageService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MainPageDAO dao;

	public List<ReviewDTO> CenterRatingList() {
		return dao.CenterRatingList();
	}

	public List<ReviewDTO> TrainerRatingList() {
		return dao.TrainerRatingList();
	}

}
