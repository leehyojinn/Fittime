package com.fitime.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fitime.dto.BlackListDTO;
import com.fitime.dto.ComplaintDTO;
import com.fitime.dto.PopupDTO;
import com.fitime.dto.TagDTO;
import com.fitime.dto.UserDTO;

@Service
public class AdminService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AdminDAO dao;
	
	 @Transactional
    public boolean popup_write(PopupDTO popupDTO) {
        try {
            // 파일 저장 처리
            if (popupDTO.getPopup_image() != null && !popupDTO.getPopup_image().isEmpty()) {
                String originalFileName = popupDTO.getPopup_image().getOriginalFilename();
                String ext = originalFileName.substring(originalFileName.lastIndexOf('.'));
                String saveFileName = UUID.randomUUID() + ext;

                // 파일 저장
                Path path = Paths.get("/usr/local/tomcat/webapps/img/popup/" + saveFileName);
                Files.write(path, popupDTO.getPopup_image().getBytes());

                // DTO에 파일명 세팅
                popupDTO.setFile_name(saveFileName);
            }

            int row = dao.popup_write(popupDTO);
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("팝업 저장 실패", e);
        }
    }

    public List<PopupDTO> getPopupList() {
        return dao.getPopupList();
    }

	public boolean toggle(Integer popup_idx) {
		
		int row = dao.toggle(popup_idx);
		
		return row > 0;
	}
	
	 @Transactional
	    public boolean popupUpdate(PopupDTO popupDTO) {
	        try {
	            // 1. 기존 파일명 조회
	            String oldFileName = dao.getFileNameById(popupDTO.getPopup_idx());
	            
	            // 2. 새 파일이 있으면 파일 교체
	            if (popupDTO.getPopup_image() != null && !popupDTO.getPopup_image().isEmpty()) {
	                // 새 파일 저장
	                String newFileName = fileSave(popupDTO.getPopup_image());
	                popupDTO.setFile_name(newFileName);
	                
	                // 기존 파일 삭제
	                if (oldFileName != null) {
	                    Path oldPath = Paths.get("/usr/local/tomcat/webapps/img/popup/" + oldFileName);
	                    Files.deleteIfExists(oldPath);
	                }
	            }
	            
	            // 3. DB 업데이트
	            int row = dao.popupUpdate(popupDTO);
	            return row > 0;
	            
	        } catch (Exception e) {
	            throw new RuntimeException("팝업 수정 실패", e);
	        }
	    }

	    @Transactional
	    public boolean popupDelete(Integer popup_idx) {
	        try {
	            // 1. 파일명 조회 후 삭제
	            String fileName = dao.getFileNameById(popup_idx);
	            if (fileName != null) {
	                Path path = Paths.get("/usr/local/tomcat/webapps/img/popup/" + fileName);
	                Files.deleteIfExists(path);
	            }
	            
	            // 2. DB 삭제
	            int row = dao.popupDelete(popup_idx);
	            return row > 0;
	            
	        } catch (Exception e) {
	            throw new RuntimeException("팝업 삭제 실패", e);
	        }
	    }

	    // 파일 저장 메서드 (기존 코드 재사용)
	    @Transactional
	    private String fileSave(MultipartFile file) throws IOException {
	        String originalFileName = file.getOriginalFilename();
	        String ext = originalFileName.substring(originalFileName.lastIndexOf('.'));
	        String saveFileName = UUID.randomUUID() + ext;
	        
	        Path path = Paths.get("/usr/local/tomcat/webapps/img/popup/" + saveFileName);
	        Files.write(path, file.getBytes());
	        
	        return saveFileName;
	    }

		public List<UserDTO> grant_search(String user_id) {
			return dao.grant_search(user_id);
		}

		public boolean grant(String user_id) {
			int row = dao.grant(user_id);
			return row > 0;
		}

		public boolean revoke(String user_id) {
			int row = dao.revoke(user_id);
			return row > 0;
		}

		public boolean tag(TagDTO dto) {
			int row = dao.tag(dto);
			return row > 0;
		}

		public List<TagDTO> tag_list(TagDTO dto) {
			return dao.tag_list(dto);
		}

		public boolean tag_update(TagDTO dto) {
			int row = dao.tag_update(dto);
			return row > 0;
		}

		public boolean tag_del(TagDTO dto) {
			int row = dao.tag_del(dto);
			return row > 0;
		}

		public List<ComplaintDTO> blacklist_list() {
		    List<ComplaintDTO> list = dao.blacklist_list();
		    for (ComplaintDTO dto : list) {
		        List<String> images = dao.selectFileNamesByReportIdx(dto.getReport_idx());
		        dto.setImages(images);
		    }
			return list;
		}

		@Transactional
		public boolean blacklist_level(String user_id, Map<String, Object> param) {
		    int row1 = dao.blacklist_level(user_id);
		    int row2 = dao.setComplaintStatusDone(param);
		    int row3 = dao.insertBlackList(user_id,param);
		    return row1 > 0 && row2 > 0 && row3>0;
		}

		public boolean blacklist_status(int report_idx, Map<String, String> params) {
		    int row = dao.blacklist_status(report_idx, params);
		    if ("처리완료".equals(params.get("status"))) {
		        dao.blacklist_level(params.get("user_id"));
		    }
		    return row > 0;
		}

		public List<BlackListDTO> blacklist() {
			return dao.blacklist();
		}

		public boolean blacklistDel(int blacklist_idx, Map<String, Object> param) {
			int row = dao.blacklistDel(blacklist_idx);
			int row2 = dao.unblacklist_level(param);
			return row>0 && row2>0 ;
		}

	
}
