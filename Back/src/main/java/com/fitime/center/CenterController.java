package com.fitime.center;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.ProductDTO;

@RestController
@CrossOrigin
public class CenterController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired
	CenterService service;
	
	// 상품추가
	@PostMapping("/product_insert")
	public Map<String, Object> product_insert(@RequestBody ProductDTO dto){
		
		result = new HashMap<String, Object>();
		
		boolean success = service.product_insert(dto);
		
		result.put("success", success);
		
		return result;
	}
	
	// 상품 리스트
	@PostMapping("/product_list/{user_id}")
	public Map<String, Object> product_list(@PathVariable String user_id){
		
		result = new HashMap<String, Object>();
		
		List<ProductDTO> list = service.product_list(user_id);
		
		result.put("list", list);
		
		return result;
	}
	
	// 상품업데이트
	@PostMapping("/product_update/{user_id}/{product_idx}")
	public Map<String, Object> product_update(@PathVariable String user_id,@PathVariable Integer product_idx, @RequestBody ProductDTO dto){
		
		result = new HashMap<String, Object>();
		
		boolean success = service.product_update(user_id,product_idx,dto);
		
		result.put("success", success);
		
		return result;
	}
	
	@PostMapping("/product_status/{user_id}/{product_idx}")
	public Map<String, Object> product_status(@PathVariable String user_id, @PathVariable Integer product_idx, @RequestBody ProductDTO dto){
		
		result = new HashMap<String, Object>();
		
		boolean success = service.product_status(user_id,product_idx,dto);
		
		result.put("success", success);
		
		return result;
	}

}
