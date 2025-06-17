package com.fitime.center;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fitime.dto.CenterProfileDTO;
import com.fitime.dto.ClassDTO;
import com.fitime.dto.ComplaintDTO;
import com.fitime.dto.FileImageDTO;
import com.fitime.dto.ProductDTO;

@Service
public class CenterService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CenterDAO dao;

	@Transactional
	public boolean complaint(ComplaintDTO dto) {
	    int row = dao.complaint(dto);
	    if (row == 0) return false;

	    int reportIdx = dto.getReport_idx(); // useGeneratedKeys로 PK 반환

	    List<FileImageDTO> fileList = new ArrayList<>();
	    if(dto.getFiles() != null && !dto.getFiles().isEmpty()) {
		    for (MultipartFile file : dto.getFiles()) {
		        try {
		            String savedFileName = fileSave(file);
		            FileImageDTO fileDto = new FileImageDTO();
		            fileDto.setReport_idx(reportIdx);
		            fileDto.setFile_name(savedFileName);
		            // 필요시 category, post_idx 등 추가 세팅
		            fileList.add(fileDto);
		        } catch (Exception e) {
		            throw new RuntimeException("파일 저장 실패", e);
		        }
		    }
	    }
	    if (!fileList.isEmpty()) {
	        dao.insertFileImages(fileList);
	    }
	    return true;
	}

	// 파일 저장 메서드
	@Transactional
	private String fileSave(MultipartFile file) throws Exception {
	    String originalFileName = file.getOriginalFilename();
	    String ext = originalFileName.substring(originalFileName.lastIndexOf('.'));
	    String saveFileName = UUID.randomUUID() + ext;
	    Path path = Paths.get("/usr/local/tomcat/webapps/img/complaint/" + saveFileName);
	    Files.write(path, file.getBytes());
	    return saveFileName;
	}

	public List<ComplaintDTO> complaint_list(String user_id) {
	    List<ComplaintDTO> list = dao.complaint_list(user_id);
	    for (ComplaintDTO dto : list) {
	        List<String> images = dao.selectFileNamesByReportIdx(dto.getReport_idx());
	        dto.setImages(images);
	    }
	    return list;
	}

	public List<ProductDTO> productList(Map<String, Object> param) {
		return dao.productList(param);
	}

	public boolean statusUpdate(int idx) {
		int row = dao.statusUpdate(idx);
		return row >0 ;
	}

	public boolean productInsert(Map<String, Object> param) {
		int row = dao.productInsert(param);
		return row>0;
	}

	public boolean productUpdate(Map<String, Object> param) {
		int row = dao.productUpdate(param);
		return row>0;
	}

	public boolean productDel(int idx) {
		int row = dao.productDel(idx);
		return row>0;
	}

	public List<ClassDTO> classList(Map<String, Object> param) {
		return dao.classList(param);
	}

	@Transactional
	public boolean classInsert(Map<String, Object> param) {
	    // 1. 클래스 등록
	    int row = dao.classInsert(param);

	    // 2. 등록 성공 시, 상품의 트레이너 정보도 업데이트
	    if(row > 0) {
	        dao.updateProductTrainer(param);
	        return true;
	    }
	    return false;
	}

	public boolean classDel(int idx) {
		int row = dao.classDel(idx);
		return row>0;
	}

	public boolean classUpdate(Map<String, Object> param) {
		int row = dao.classUpdate(param);
		return row>0;
	}

	public List<Map<String, Object>> trainerList(String id) {
		return dao.trainerList(id);
	}

	public boolean trainerDel(int idx) {
		int row = dao.trainerDel(idx);
		return row>0?true:false;
	}


	public List<Map<String, Object>> searchTrainers(Map<String, Object> param) {
		String id = (String)param.get("id");
		return dao.searchTrainers(id);
	}

	public boolean addTrainer(Map<String, Object> param) {
		int row = dao.addTrainer(param);
		return row>0?true:false;
	}

	public List<CenterProfileDTO> center_profile(String center_id) {

		return dao.center_profile(center_id);

	}

}
