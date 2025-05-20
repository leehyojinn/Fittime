package com.fitime.research;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.CenterProfileDTO;

@Mapper
public interface ResearchDAO {

	List<CenterProfileDTO> searchLocation(Map<String, Object> param);

}
