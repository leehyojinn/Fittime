package com.fitime.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.PopupDTO;

@Mapper
public interface AdminDAO {

	int popup_write(PopupDTO popupDTO);

	List<PopupDTO> getPopupList();

	int toggle(Integer popup_idx);
	
	String getFileNameById(Integer popup_idx);
	
	int popupUpdate(PopupDTO popupDTO);
	
	int popupDelete(Integer popup_idx);

}
