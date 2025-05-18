package com.fitime.admin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.PopupDTO;

@RestController
@CrossOrigin
public class AdminController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired
	AdminService service;
	
	// 등록
    @PostMapping(value = "/popup_write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> popup_write(@ModelAttribute PopupDTO popupDTO) {
        Map<String, Object> result = new HashMap<>();
        boolean success = service.popup_write(popupDTO);
        result.put("success", success);
        return result;
    }
    
    // 리스트
    @GetMapping("/popup_list")
    public Map<String, Object> getPopupList() {
        result = new HashMap<String,Object>();
        List<PopupDTO> list = service.getPopupList();
        result.put("data", list);
        return result;
    }
    
    // 토글업데이트
    @GetMapping("/toggle/{popup_idx}")
    public Map<String, Object> toggle(@PathVariable Integer popup_idx){
    	
        result = new HashMap<String,Object>();
        
        boolean success = service.toggle(popup_idx);
        
        result.put("success", success);
        	
    	return result;
    }
    
    // 이미지
    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        Path path = Paths.get("C:/img/popup/" + fileName);
        Resource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG) // 필요에 따라 타입 변경
            .body(resource);
    }
    
    // 팝업 수정 (파일 포함)
    @PostMapping(value = "/popup_update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> popupUpdate(@ModelAttribute PopupDTO popupDTO) {
        Map<String, Object> result = new HashMap<>();
        boolean success = service.popupUpdate(popupDTO);
        result.put("success", success);
        return result;
    }

    // 팝업 삭제
    @PostMapping("/popup_delete/{popup_idx}")
    public Map<String, Object> popupDelete(@PathVariable Integer popup_idx) {
        Map<String, Object> result = new HashMap<>();
        boolean success = service.popupDelete(popup_idx);
        result.put("success", success);
        return result;
    }
    
}
	



