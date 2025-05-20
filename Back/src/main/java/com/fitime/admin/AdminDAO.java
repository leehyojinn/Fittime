package com.fitime.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fitime.dto.ComplaintDTO;
import com.fitime.dto.PopupDTO;
import com.fitime.dto.TagDTO;
import com.fitime.dto.UserDTO;

@Mapper
public interface AdminDAO {

	int popup_write(PopupDTO popupDTO);

	List<PopupDTO> getPopupList();

	int toggle(Integer popup_idx);
	
	String getFileNameById(Integer popup_idx);
	
	int popupUpdate(PopupDTO popupDTO);
	
	int popupDelete(Integer popup_idx);

	List<UserDTO> grant_search(String user_id);

	int grant(String user_id);

	int revoke(String user_id);

	int tag(TagDTO dto);

	List<TagDTO> tag_list(TagDTO dto);

	int tag_update(TagDTO dto);

	int tag_del(TagDTO dto);

	List<String> selectFileNamesByReportIdx(int report_idx);

	List<ComplaintDTO> blacklist_list();

	int blacklist_level(String user_id);

	int blacklist_status(String user_id, @Param("params") Map<String, String> params);

	int setComplaintStatusDone(String user_id);

}
