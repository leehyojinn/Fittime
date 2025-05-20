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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitime.dto.ComplaintDTO;
import com.fitime.dto.PopupDTO;
import com.fitime.dto.TagDTO;
import com.fitime.dto.UserDTO;

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
    
    // 관리자권한 부여 아이디 검색
    @PostMapping("/grant_search/{user_id}")
    public Map<String, Object> grant_search(@PathVariable String user_id){
    	result = new HashMap<String, Object>();
    	
    	List<UserDTO> list = service.grant_search(user_id);
    	
    	result.put("list", list);
    	
    	return result;
    }
    
    // 권한 부여
    @PostMapping("/grant/{user_id}")
    public Map<String, Object> grant(@PathVariable String user_id){
    	
    	result = new HashMap<String, Object>();
    	
    	boolean success = service.grant(user_id);
    	
    	result.put("success", success);
    	
    	return result;
    }
    
    // 권한 해제
    @PostMapping("/revoke/{user_id}")
    public Map<String, Object> revoke(@PathVariable String user_id){
    	
    	result = new HashMap<String, Object>();
    	
    	boolean success = service.revoke(user_id);
    	
    	result.put("success", success);
    	
    	return result;
    }
    
    //태그 등록
    @PostMapping("/tag")
    public Map<String, Object> tag(@RequestBody TagDTO dto){
    	
    	result = new HashMap<String, Object>();
    	
    	boolean success = service.tag(dto);
    	
    	result.put("success", success);
    	
    	return result;
    }
    
    //태그 리스트
    @PostMapping("/tag_list")
    public Map<String, Object> tag_list(@RequestBody TagDTO dto){
    	result = new HashMap<String, Object>();
    	
    	List<TagDTO> list = service.tag_list(dto);
    	
    	result.put("list", list);
    	
    	return result;
    }
    
    //태그 업데이트
    @PostMapping("/tag_update")
    public Map<String, Object> tag_update(@RequestBody TagDTO dto){
    	
    	result = new HashMap<String, Object>();
    	
    	boolean success = service.tag_update(dto);
    	
    	result.put("success", success);
    	
    	return result;
    	
    }
    
	/* 태그삭제 */
    @PostMapping("/tag_del")
    public Map<String, Object> tag_del(@RequestBody TagDTO dto){
    	
    	result = new HashMap<String, Object>();
    	
    	boolean success = service.tag_del(dto);
    	
    	result.put("success", success);
    	
    	return result;
    	
    }
    
    //블랙리스트_리스트
    @PostMapping("/blacklist_list")
    public Map<String, Object> blacklist_list(){
    	
    	result = new HashMap<String, Object>();
    	
    	List<ComplaintDTO> list = service.blacklist_list();
    	
    	result.put("list", list);
    	
    	return result;
    }
    
    //블랙리스트 레벨 변경
    @PostMapping("/blacklist_level/{user_id}")
    public Map<String, Object> blacklist_level(@PathVariable String user_id){
    	
    	result = new HashMap<String, Object>();
    	
    	boolean success = service.blacklist_level(user_id);
    	
    	result.put("success", success);
    	
    	return result;
    }
    
    // 블랙리스트 상태변경
    @PostMapping("/blacklist_status/{user_id}")
    public Map<String, Object> blacklist_status(@PathVariable String user_id, @RequestBody Map<String, String> params){
    	
    	result = new HashMap<String, Object>();
    	
    	boolean success = service.blacklist_status(user_id,params);
    	
    	result.put("success", success);
    	
    	return result;
    }
}
	



