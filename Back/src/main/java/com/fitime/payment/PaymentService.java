package com.fitime.payment;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitime.dto.BuyListDTO;
import com.fitime.dto.KakaoDTO;
import com.fitime.dto.PaymentDTO;
import com.fitime.reservation.ReservationService;

@Service
public class PaymentService {

	@Autowired ReservationService reservationService;
	@Autowired PaymentDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Transactional
	public boolean paymentInsert(Map<String, Object> param) {
		boolean booked = reservationService.booking(param);
		int row = dao.paymentInsert(param);
		if(row>0) {
			row = dao.buyListInsert(param);
		}
		return row>0&&booked;
	}
	
	public boolean paymentCancel(int idx) {
		int row = dao.paymentCancel(idx);
		if(row>0) {
			row = dao.buyListDel(idx);
		}
		return row>0;
	}
	
	public boolean kakaoReady(Map<String, String> param) {
		logger.info("param : {}",param);
		String url = param.get( "next_redirect_pc_url");
		String[] parts = url.split("/");
		String pg_token = parts[4];
		param.put("pg_token", pg_token);
		int row = dao.kakaoReady(param);
		
		return row>0;
	}
	
	public boolean kakaoApprove(Map<String, Object> param) {
		int row = 0;
		switch ((String)param.get( "payment_method_type")) {
		case "CARD":
			dao.kakaoApprove(param);
			//row = dao.kakaoCARD(param);
			break;
		case "MONEY":
			dao.kakaoApprove(param);
			//row = dao.kakaoMONEY(param);
			break;
		default:
			break;
		}
		
		return row>0;
	}

	public List<BuyListDTO> getBuyList(String id) {
		return dao.getBuyList(id);
	}

	public List<PaymentDTO> getPayment(String id) {
		return dao.getPayment(id);
	}

	public List<KakaoDTO> getKakaoByUser(String id) {
		return dao.getKakaoByUser(id);
	}
	
	
}
