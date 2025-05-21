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

import com.fitime.dto.ComplaintDTO;
import com.fitime.dto.FileImageDTO;
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

	@Transactional
	public boolean complaint(ComplaintDTO dto) {
	    int row = dao.complaint(dto);
	    if (row == 0) return false;

	    int reportIdx = dto.getReport_idx(); // useGeneratedKeys로 PK 반환

	    List<FileImageDTO> fileList = new ArrayList<>();
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
	    if (!fileList.isEmpty()) {
	        dao.insertFileImages(fileList);
	    }
	    return true;
	}

	// 파일 저장 메서드
	private String fileSave(MultipartFile file) throws Exception {
	    String originalFileName = file.getOriginalFilename();
	    String ext = originalFileName.substring(originalFileName.lastIndexOf('.'));
	    String saveFileName = UUID.randomUUID() + ext;
	    Path path = Paths.get("C:/img/complaint/" + saveFileName);
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

}
