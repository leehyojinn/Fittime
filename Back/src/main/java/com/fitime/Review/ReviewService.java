package com.fitime.Review;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fitime.dto.ReviewDTO;

@Service
public class ReviewService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired ReviewDAO dao;
	
	// 리뷰 이미지 파일 저장 링크
	String root = "C:/img/review/";
	
	// 리뷰 이미지 파일 저장
	private boolean fileSaveReview(MultipartFile[] files, int idx) {
		
		boolean success = true;
		
			String filename = "";
			String extension = "";
			
	    for ( MultipartFile file : files) {
	    	
	    	filename = file.getOriginalFilename();
	    	extension = filename.substring(filename.lastIndexOf("."));
	    	filename = UUID.randomUUID() + extension;
	    
	    try {
	    
	    	byte[] arr = file.getBytes();
	    	Path path = Paths.get("C:/img/review/" + filename);
	    	Files.write(path, arr);
	    	dao.reviewImgSave(idx, filename);
		
	    } catch (IOException e) {
			e.printStackTrace();
			success = false;
		}
	} 
	    
	    return success ;
	}
	    
	private boolean fileDelReview(int idx) {
		 
		boolean success = true;
		int row = 0;
		
	
		List<String> Delfilenames = dao.reviewImgSearch(idx);
		
		if ( Delfilenames != null ) { 
			
			for  ( String Delfilename : Delfilenames ) {
				
				try {
			    	Path path = Paths.get("C:/img/review/" + Delfilename);
			    	Files.deleteIfExists(path);
				} catch (IOException e) {
					e.printStackTrace();
					success = false;
				}
			}
			row = dao.fileDelReview(idx);
		}
		
		return success && row >0;
	}


	//list
	public Map<String, Object> listReview(String page, Map<String, Object> params) {
		
		int totalPage = dao.pages();
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		int listPage = Integer.parseInt(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ( totalPage >= listPage ) {
			int offset = (listPage-1) * 10 ;
			list = dao.listReview(offset);
			
			map.put("list", list);
			map.put("pages", totalPage);
			map.put("page", page);
			map.put("total", dao.totalPage());
		}
		
		return map;
	}

	public boolean insertReview(MultipartFile[] files, ReviewDTO dto) {
		int row = dao.insertReview(dto);
		boolean success = true;
		if(files != null) // 이미지가 들어왔을 때
		{
			int idx = dto.getReview_idx();
			logger.info("idx : "+idx);
			success = fileSaveReview(files,idx);
		}
		
		return row > 0 && success ;
	}

	public boolean delReview(int review_idx) {
		boolean success = fileDelReview(review_idx);
		int row = dao.delReview(review_idx);
		return row > 0 && success;
	}

	public ReviewDTO detailReview(ReviewDTO dto) {
		return dao.detailReview(dto);
	}

	public boolean updateReview(MultipartFile[] files, ReviewDTO dto) {
		int row = dao.updateReview(dto);		
		if(files != null) // 이미지가 들어왔을 때
		{
			int idx = dto.getReview_idx();
			fileDelReview(idx);
			fileSaveReview(files,idx); 
		}
		return row > 0 ? true : false;
	}

	public int overayReview(ReviewDTO dto) {
		return dao.overayReview(dto);
	}

	public List<Map<String, String>> reviewByUser(Map<String, String> param) {
		return dao.reviewByUser(param);
	}

	public List<Map<String, Object>> reviewByTrainer(Map<String, String> param) {
		return dao.reviewByTrainer(param);
	}

}
