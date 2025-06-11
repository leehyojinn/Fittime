package com.fitime.payment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fitime.dto.BuyListDTO;
import com.fitime.dto.KakaoDTO;
import com.fitime.dto.PaymentDTO;

@Mapper
public interface PaymentDAO {

	int paymentInsert(Map<String, Object> param);

	int buyListInsert(Map<String, Object> param);

	int paymentCancel(int idx);

	int buyListDel(int idx);

	int kakaoApprove(Map<String, Object> param);

	int kakaoReady(Map<String, String> param);

	List<BuyListDTO> getBuyList(String id);

	List<PaymentDTO> getPayment(String id);

	List<KakaoDTO> getKakaoByUser(String id);

}
