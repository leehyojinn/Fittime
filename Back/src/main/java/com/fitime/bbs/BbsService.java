package com.fitime.bbs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import com.fitime.dto.BbsDTO;

@Service
public class BbsService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BbsDAO dao;
	
	@Transactional
	private boolean fileSave(BbsDTO dto) {
		boolean success = true;
		MultipartFile[] files = dto.getFiles();
		String filename = "";
		String ext = "";
		
		for (MultipartFile file : files) {
			filename = file.getOriginalFilename();
			ext = filename.substring(filename.lastIndexOf("."));
			filename = UUID.randomUUID().toString()+ext;
			
			try {
				byte[] arr = file.getBytes();
				Path path = Paths.get("/usr/local/tomcat/webapps/img/board/"+filename);
				Files.write(path, arr);
				dao.ImgSave(filename,dto.getBoard_idx());
			} catch (IOException e) {
				e.printStackTrace();
				success= false;
			}
		}
		return success;
	}
	@Transactional
	private boolean fileDel(int idx) {
		boolean success = true;
		List<String> filenames = dao.ImgSearch(idx);
		if(filenames != null) {
			for (String filename : filenames) {
				try {
					Path path = Paths.get("/usr/local/tomcat/webapps/img/board/"+filename);
					Files.delete(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					success = false;
				}
			}
			dao.ImgDel(idx);
		}
		return success;
	}
	@Transactional
	public boolean writeBbs(BbsDTO dto) {
		int row = dao.writeBbs(dto);
		boolean success = true;
		if(dto.getFiles() != null) {
			success = fileSave(dto);
		}
		return row > 0 && success ;
	}
	@Transactional
	public boolean updateBbs(BbsDTO dto) {
		int row = dao.updateBbs(dto);
		boolean success = true;
		if(dto.getFiles() != null) {
			fileDel(dto.getBoard_idx());
			success = fileSave(dto);
		}
		return row > 0 && success;
	}
	@Transactional
	public boolean delBbs(int board_idx) {
		boolean success = true;
		int cnt = dao.findFiles(board_idx);
		if(cnt > 0) {
			success =fileDel(board_idx);
		}
		int row = dao.delBbs(board_idx);
		return row>0 && success;
	}

	public Map<String, Object> listBbs(Map<String, Object>param) {
		Map<String, Object> result= new HashMap<String, Object>();
		int cnt = 10; // 페이지당 리스트 갯수
		Map<String, Object>queryParam = new HashMap<String, Object>(param);
		queryParam.put("cnt", cnt);
		int totalpage = dao.getTotalPage(queryParam);
		int offset = ((int)param.get("page")-1)*cnt;
		queryParam.put("offset", offset);
		List<Map<String, Object>>list = dao.listBbs(queryParam);
		result.put("list", list);
		result.put("totalpage", totalpage);
		return result;
	}

	public BbsDTO detailBbs(int idx) {
		return dao.detailBbs(idx);
	}

	public List<Map<String, Object>> findphotos(int idx) {
		return dao.findphotos(idx);
	}
	
	public ResponseEntity<Resource> getImg(int file_idx) {
		Resource res = null;
		HttpHeaders headers = new HttpHeaders();
		
		Map<String, String> fileMap = dao.getImg(file_idx);
		logger.info("fileMap : {}",fileMap);
		res = new FileSystemResource("/usr/local/tomcat/webapps/img/board/"+fileMap.get("file_name"));
		logger.info("res : "+res);
		
		try {
			String content_type = Files.probeContentType(Paths.get("/usr/local/tomcat/webapps/img/board"+fileMap.get("file_name")));
			headers.add("Content-Type", content_type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(res,headers,HttpStatus.OK);

	}

	public boolean writeComment(Map<String, Object> param) {
		int row = dao.writeCommnet(param);
		return row > 0;
	}

	public boolean updateComment(Map<String, Object> param) {
		int row = dao.updateComment(param);
		return row>0;
	}

	public boolean delCommnet(int idx) {
		int row = dao.delComment(idx);
		return row>0;
	}

	public List<Map<String, Object>> listComment(int idx) {
		return dao.listComment(idx);
	}

	public boolean writeReply(Map<String, Object> param) {
		int row = 0;
		int cnt = dao.overlayReply((int)param.get("comment_idx"));
		if(cnt > 0) {
			return false;
		}else {
			row = dao.writeReply(param);
		}
		return row > 0;
	}
	
	public boolean updateReply(Map<String, Object> param) {
		int row = dao.updateReply(param);
		return row>0;
	}
	public boolean delReply(int idx) {
		int row = dao.delReply(idx);
		return row>0;
	}

	public List<Map<String, Object>> listReply(int idx) {
		return dao.listReply(idx);
	}
}
