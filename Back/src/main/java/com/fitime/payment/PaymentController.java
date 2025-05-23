package com.fitime.payment;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.PaymentDTO;

@RestController
public class PaymentController {

	@Autowired PaymentService service;
	Logger logger = LoggerFactory.getLogger(getClass());
	Map<String, Object>result = null;
	
	@PostMapping(value="/insert/payment")
	public Map<String, Object>paymentInsert(@RequestBody PaymentDTO dto){
		logger.info("dto : {}",dto.toString());
		result = new HashMap<String, Object>();
		boolean success = service.paymentInsert(dto);
		result.put("success", success);
		return result;
	}

	@PostMapping(value="/cancel/payment/{payment_idx}")
	public Map<String, Object>paymentCancel(@PathVariable int payment_idx){
		logger.info("payment_idx = " + payment_idx);
		result = new HashMap<String, Object>();
		boolean success = service.paymentCancel(payment_idx);
		result.put("success", success);
		return result;
	}
	
	@PostMapping(value="/ready/kakao")
	public Map<String, Object>kakaoReady(@RequestBody Map<String, String>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.kakaoReady(param);
		result.put("success", success);
		return result;
	}
	
	@PostMapping(value="/approve/kakao")
	public Map<String, Object>kakaoApprove(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.kakaoApprove(param);
		result.put("success", success);
		return result;
	}
	
	
	
}
