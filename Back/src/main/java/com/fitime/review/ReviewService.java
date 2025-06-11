package com.fitime.review;

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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fitime.dto.ReviewDTO;

@Service
public class ReviewService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired ReviewDAO dao;
	
	// 리뷰 이미지 파일 저장 링크
	String root = "C:/img/review/";
	
	// 리뷰 이미지 파일 저장
	@Transactional
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
	    
	@Transactional
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
	public Map<String, Object> listReview(String page) {
		
		int totalPage = dao.pages();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
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

	@Transactional
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

	@Transactional
	public boolean delReview(int review_idx) {
		boolean success = true;
		int cnt = dao.findFiles(review_idx);
		int row = dao.delReview(review_idx);
		if(cnt > 0) {
			success = fileDelReview(review_idx);
		}
		return row > 0 && success;
	}

	public ReviewDTO detailReview(ReviewDTO dto) {
		return dao.detailReview(dto);
	}

	@Transactional
	public boolean updateReview(ReviewDTO dto) {
		int row = dao.updateReview(dto);
		if(dto.getFiles() != null) // 이미지가 들어왔을 때
		{
			int idx = dto.getReview_idx();
			fileDelReview(idx);
			fileSaveReview(dto.getFiles(),idx); 
		}
		return row > 0 ? true : false;
	}

	public int overayReview(ReviewDTO dto) {
		return dao.overayReview(dto);
	}

	public Map<String, Object> reviewByUser(String page, Map<String, Object> param) {
		int pageSize = 5;
		int offset = (Integer.parseInt(page)-1)*pageSize;
		Map<String, Object>copyParam = new HashMap<String, Object>(param);
		copyParam.put("pageSize", pageSize);
		copyParam.put("offset", offset);
		int totalPage = dao.totalPageByUser(copyParam);
		List<Map<String, Object>>list = dao.reviewByUser(copyParam);
		Map<String, Object>result = new HashMap<String, Object>();
		result.put("reviews", list);
		result.put("totalPage", totalPage);
		result.put("page", Integer.parseInt(page));
		return result;
	}


	public Map<String, Object> reviewByTrainer(String page, Map<String, Object> param) {
		int pageSize = 5;
		int offset = (Integer.parseInt(page)-1)*pageSize;
		Map<String, Object>copyParam = new HashMap<String, Object>(param);
		copyParam.put("pageSize", pageSize);
		copyParam.put("offset", offset);
		int totalPage = dao.totalPageByTrainer(copyParam);
		List<Map<String, Object>>list = dao.reviewByTrainer(copyParam);
		Map<String, Object>result = new HashMap<String, Object>();
		result.put("reviews", list);
		result.put("totalPage", totalPage);
		result.put("page", Integer.parseInt(page));
		return result;
	}

	public Map<String, Object> reviewByCenter(String page, Map<String, Object> param) {
		int pageSize = 5;
		int offset = (Integer.parseInt(page)-1)*pageSize;
		Map<String, Object>copyParam = new HashMap<String, Object>(param);
		copyParam.put("pageSize", pageSize);
		copyParam.put("offset", offset);
		int totalPage = dao.totalPageByCenter(copyParam);
		List<Map<String, Object>>list = dao.reviewByCenter(copyParam);
		Map<String, Object>result = new HashMap<String, Object>();
		result.put("reviews", list);
		result.put("totalPage", totalPage);
		result.put("page", Integer.parseInt(page));
		return result;
	}
	
	public ResponseEntity<Resource> getImg(int file_idx) {
		Resource res = null;
		HttpHeaders headers = new HttpHeaders();
		
		Map<String, String> fileMap = dao.getImg(file_idx);
		logger.info("fileMap : {}",fileMap);
		res = new FileSystemResource("C:/img/review/"+fileMap.get("file_name"));
		logger.info("res : "+res);
		
		try {
			String content_type = Files.probeContentType(Paths.get("C:/img/review"+fileMap.get("file_name")));
			headers.add("Content-Type", content_type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(res,headers,HttpStatus.OK);

	}

	public Map<String, Object> getReview(int idx) {
		return dao.getReview(idx);
	}

	public int findFiles(int review_idx) {
		return dao.findFiles(review_idx);
	}

	public List<Map<String, Object>> getPhotos(int review_idx) {
		return dao.getPhotos(review_idx);
	}

}
