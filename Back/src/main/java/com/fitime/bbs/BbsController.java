package com.fitime.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.BbsDTO;

@CrossOrigin(
	    origins = "http://192.168.0.114:3000",
	    allowedHeaders = "*",
	    allowCredentials = "true"
	)
@RestController
public class BbsController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BbsService service;
	Map<String, Object> result = null;
	
	@PostMapping(value="/write/bbs")
	public Map<String, Object> writeBbs(@ModelAttribute BbsDTO dto){
		logger.info("category : " + dto.getCategory());
		result = new HashMap<String, Object>();
		boolean success = service.writeBbs(dto);
		result.put("success", success);
		result.put("board_idx", dto.getBoard_idx());
		return result;
	}
	
	@PostMapping(value="/update/bbs")
	public Map<String, Object> updateBbs(@ModelAttribute BbsDTO dto){
		result = new HashMap<String, Object>();
		boolean success = service.updateBbs(dto);
		result.put("success", success);
		return result;
	}
	
	@PostMapping(value="/del/bbs/{board_idx}")
	public Map<String, Object>delBbs(@PathVariable int board_idx){
		result = new HashMap<String, Object>();
		boolean success = service.delBbs(board_idx);
		result.put("success", success);
		return result;
	}
	
	@PostMapping(value="/list/bbs")
	public Map<String, Object>listBbs(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		return service.listBbs(param);
	}
	
	@PostMapping(value="/detail/bbs/{board_idx}")
	public Map<String, Object>detailBbs(@PathVariable int board_idx){
		result = new HashMap<String, Object>();
		BbsDTO dto = service.detailBbs(board_idx);
		List<Map<String, Object>> files = service.findphotos(board_idx);
		result.put("dto", dto);
		result.put("photos", files);
		return result;
	}
	
	// 사진 가져오기
	@GetMapping(value="/bbsImg/{file_idx}")
	public ResponseEntity<Resource> img(@PathVariable String file_idx){
		int idx = Integer.parseInt(file_idx);
		return service.getImg(idx);
	}
	
	@PostMapping(value="/write/comment")
	public Map<String, Object> writeComment(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.writeComment(param);
		result.put("success", success);
		return result;
	}
	
	@PostMapping(value="/update/comment")
	public Map<String, Object> updateComment(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.updateComment(param);
		result.put("success", success);
		return result;
	}
	
	@PostMapping(value="/list/comment/{board_idx}")
	public Map<String, Object>listComment(@PathVariable int board_idx){
		result = new HashMap<String, Object>();
		List<Map<String, Object>>list = service.listComment(board_idx);
		result.put("comments", list);
		return result;
	}
	
	@PostMapping(value="/del/comment/{comment_idx}")
	public Map<String, Object>delComment(@PathVariable int comment_idx){
		result = new HashMap<String, Object>();
		boolean success = service.delCommnet(comment_idx);
		result.put("success", success);
		return result;
	}
	
	@PostMapping(value="/write/reply")
	public Map<String, Object> writeReply(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.writeReply(param);
		result.put("success", success);
		return result;
	}
	
	@PostMapping(value="/update/reply")
	public Map<String, Object> updateReply(@RequestBody Map<String, Object>param){
		logger.info("param : {}",param);
		result = new HashMap<String, Object>();
		boolean success = service.updateReply(param);
		result.put("success", success);
		return result;
	}
	
	@PostMapping(value="/list/reply/{board_idx}")
	public Map<String, Object>listReply(@PathVariable int board_idx){
		result = new HashMap<String, Object>();
		List<Map<String, Object>>reply = service.listReply(board_idx);
		result.put("reply", reply);
		return result;
	}
	
	@PostMapping(value="/del/reply/{reply_idx}")
	public Map<String, Object>delReply(@PathVariable int reply_idx){
		result = new HashMap<String, Object>();
		boolean success = service.delReply(reply_idx);
		result.put("success", success);
		return result;
	}
}
