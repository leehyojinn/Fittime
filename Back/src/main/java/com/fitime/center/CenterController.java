package com.fitime.center;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fitime.dto.CenterProfileDTO;
import com.fitime.dto.ClassDTO;
import com.fitime.dto.ComplaintDTO;
import com.fitime.dto.ProductDTO;

@RestController
@CrossOrigin
public class CenterController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired
	CenterService service;
	
	// 신고하기
	@PostMapping("/complaint")
	public Map<String, Object> complaint(
	    @RequestPart("complaint") ComplaintDTO dto,
	    @RequestPart("files") List<MultipartFile> files) {

	    dto.setFiles(files);
	    boolean success = service.complaint(dto);

	    Map<String, Object> result = new HashMap<>();
	    result.put("success", success);
	    return result;
	}
	
	// 신고하기 리스트
	@PostMapping("/complaint_list/{user_id}")
	public Map<String, Object> complaint_list(@PathVariable String user_id){
		
		result = new HashMap<String, Object>();
		
		List<ComplaintDTO> list = service.complaint_list(user_id);
		
		result.put("list", list);
		
		return result;
	}

	//상품 리스트
	@PostMapping(value="/list/product")
	public Map<String, Object> productList(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		List<ProductDTO>list = service.productList(param); 
		result.put("products", list);
		return result;
	}
	
	//status 수정(활성/비활성)
	@GetMapping(value="/update/productStatus/{product_idx}")
	public Map<String, Object> statusUpdate(@PathVariable int product_idx){
		logger.info("product_idx : "+product_idx);
		result = new HashMap<String, Object>();
		boolean success = service.statusUpdate(product_idx);
		result.put("success", success);
		return result;
	}
	
	//product 등록
	@PostMapping(value="/insert/product")
	public Map<String, Object>productInsert(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.productInsert(param);
		result.put("success",success );
		return result;
	}
	
	//product 수정
	@PostMapping(value="/update/product")
	public Map<String, Object> productUpdate(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.productUpdate(param);
		result.put("success",success );
		return result;
	}
	
	//product 삭제
	@PostMapping(value="/del/product/{product_idx}")
	public Map<String, Object> productDel(@PathVariable int product_idx){
		logger.info("product_idx : "+product_idx);
		result = new HashMap<String, Object>();
		boolean success = service.productDel(product_idx);
		result.put("success",success );
		return result;
	}
	
	// class 등록
	@PostMapping(value="/insert/class")
	public Map<String, Object>classInsert(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.classInsert(param);
		result.put("success",success);
		return result;
	}
	// class 리스트
	@PostMapping(value="/list/class")
	public Map<String, Object>classList(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		List<ClassDTO>list = service.classList(param); 
		result.put("list", list);
		return result;
	} 
	
	// class 수정
	@PostMapping(value="/update/class")
	public Map<String, Object>classUpdate(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.classUpdate(param);
		result.put("success",success);
		return result;
	}
	
	// class 삭제
	@DeleteMapping(value="/del/class/{class_idx}")
	public Map<String, Object>classDel(@PathVariable int class_idx){
		logger.info("class_idx : "+class_idx);
		result = new HashMap<String, Object>();
		boolean success = service.classDel(class_idx);
		result.put("success",success);
		return result;
	}
	
	// 소속 트레이너 리스트
	@PostMapping(value="/list/trainers/{center_id}")
	public Map<String, Object>trainerList(@PathVariable String center_id){
		logger.info("center_id : "+center_id);
		result = new HashMap<String, Object>();
		List<Map<String, Object>>list = service.trainerList(center_id);
		result.put("trainers", list);
		return result ;
	}
	
	// 소속 트레이너 삭제
	@PostMapping(value="/del/trainers/{trainer_idx}")
	public Map<String, Object>trainerDel(@PathVariable int trainer_idx){
		logger.info("trainer_idx : "+trainer_idx);
		result = new HashMap<String, Object>();
		boolean success = service.trainerDel(trainer_idx);
		result.put("success",success);
		return result;
	}
	

	// 소속 트레이너 검색
	@PostMapping(value="/search/trainers")
	public Map<String, Object>searchTrainers(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		List<Map<String, Object>>trainers = service.searchTrainers(param);
		result.put("trainers", trainers);
		return result;
	}
	
	// 소속 트레이너 추가
	@PostMapping(value="/add/trainer")
	public Map<String, Object>addTrainer(@RequestBody Map<String, Object>param){
		logger.info("parma : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.addTrainer(param);
		result.put("success", success);
		return result;
	}

	@PostMapping("/center_profile/{center_id}")
	public Map<String, Object> center_profile(@PathVariable String center_id){
		result = new HashMap<String, Object>();
		
		List<CenterProfileDTO> list = service.center_profile(center_id);
		
		result.put("list", list);
		
		return result;
	}
	
}
