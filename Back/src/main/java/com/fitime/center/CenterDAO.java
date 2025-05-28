package com.fitime.center;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fitime.dto.ClassDTO;
import com.fitime.dto.ComplaintDTO;
import com.fitime.dto.FileImageDTO;
import com.fitime.dto.ProductDTO;

@Mapper
public interface CenterDAO {

	int product_insert(ProductDTO dto);

	List<ProductDTO> product_list(String user_id);

	int product_update(
		    @Param("user_id") String user_id,
		    @Param("product_idx") Integer product_idx,
		    @Param("dto") ProductDTO dto
		);

	int product_status(@Param("user_id") String user_id, @Param("product_idx") Integer product_idx, @Param("dto") ProductDTO dto);

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

}
