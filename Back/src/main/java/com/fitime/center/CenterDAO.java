package com.fitime.center;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
