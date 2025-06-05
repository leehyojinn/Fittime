package com.fitime.mainpage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.ReviewDTO;

@Mapper
public interface MainPageDAO {

	List<ReviewDTO> CenterRatingList();

	List<ReviewDTO> TrainerRatingList();

}
