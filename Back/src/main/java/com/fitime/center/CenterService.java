package com.fitime.center;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitime.dto.ProductDTO;

@Service
public class CenterService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CenterDAO dao;

	public boolean product_insert(ProductDTO dto) {
		
		int row = dao.product_insert(dto);
		
		return row > 0;
	}

	public List<ProductDTO> product_list(String user_id) {
		return dao.product_list(user_id);
	}

	public boolean product_update(String user_id, Integer product_idx, ProductDTO dto) {
		int row = dao.product_update(user_id,product_idx,dto);
		return row > 0;
	}

	public boolean product_status(String user_id, Integer product_idx, ProductDTO dto) {
		int row = dao.product_status(user_id,product_idx,dto);
		return row>0;
	}

}
