package com.fitime.center;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.CenterProfileDTO;
import com.fitime.dto.ClassDTO;
import com.fitime.dto.ComplaintDTO;
import com.fitime.dto.FileImageDTO;
import com.fitime.dto.ProductDTO;

@Mapper
public interface CenterDAO {

	int complaint(ComplaintDTO dto); // 신고 insert
	int insertFileImages(List<FileImageDTO> files); // 파일 다중 insert

	List<ComplaintDTO> complaint_list(String user_id);

	List<String> selectFileNamesByReportIdx(int report_idx);

	List<ProductDTO> productList(Map<String, Object> param);

	int statusUpdate(int idx);

	int productInsert(Map<String, Object> param);

	int productUpdate(Map<String, Object> param);

	int productDel(int idx);

	List<ClassDTO> classList(Map<String, Object> param);

	int classInsert(Map<String, Object> param);

	int classDel(int idx);

	int classUpdate(Map<String, Object> param);

	List<Map<String, Object>> trainerList(String id);

	int trainerDel(int idx);
	
	List<CenterProfileDTO> center_profile(String center_id);
	
	void updateProductTrainer(Map<String, Object> param);

	List<Map<String, Object>> searchTrainers(String id);

	int addTrainer(Map<String, Object> param);

}
