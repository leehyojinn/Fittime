package com.fitime.kakaopay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoPayService {

    @Autowired
    private KakaoPayDAO kakaoPayDAO;

    public Map<String, Object> kakaoPayReady(String userId, int productIdx, String productName, int price) {
        String adminKey = "dc833fe6f0ef86dbcf713dead206e406";
        String cid = "TC0ONETIME";
        String orderId = "ORDER_" + System.currentTimeMillis();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", cid);
        params.add("partner_order_id", orderId);
        params.add("partner_user_id", userId);
        params.add("item_name", productName);
        params.add("quantity", "1");
        params.add("total_amount", String.valueOf(price));
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://13.125.180.220:3000/component/kakaopay/success");
        params.add("cancel_url", "http://13.125.180.220:3000/component/kakaopay/cancel");
        params.add("fail_url", "http://13.125.180.220:3000/component/kakaopay/fail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response = restTemplate.postForEntity(
            "https://kapi.kakao.com/v1/payment/ready",
            body,
            Map.class
        );

        Map<String, Object> kakaoRes = response.getBody();
        String tid = (String) kakaoRes.get("tid");
        String next_redirect_pc_url = (String) kakaoRes.get("next_redirect_pc_url");

        // DB에 결제 준비 정보 저장
        Map<String, Object> kakaoParam = new HashMap<>();
        kakaoParam.put("user_id", userId);
        kakaoParam.put("tid", tid);
        kakaoParam.put("order_id", orderId);
        kakaoParam.put("product_idx", productIdx);
        kakaoParam.put("total_amount", price);
        kakaoParam.put("status", "준비");
        kakaoPayDAO.insertKakaoPayment(kakaoParam);

        Map<String, Object> result = new HashMap<>();
        result.put("qr_url", next_redirect_pc_url);
        result.put("tid", tid);
        return result;
    }

    public Map<String, Object> getKakaoPaymentByTid(String tid) {
        return kakaoPayDAO.getKakaoPaymentByTid(tid);
    }

    public void updateKakaoPaymentStatus(String tid, String status) {
        kakaoPayDAO.updateKakaoPaymentStatus(tid, status);
    }
    
    public boolean approveKakaoPayment(String tid, String pgToken) {
        // 결제 준비 시 저장한 주문번호, 유저ID를 DB에서 조회
        String partnerOrderId = kakaoPayDAO.getOrderIdByTid(tid);
        String partnerUserId = kakaoPayDAO.getUserIdByTid(tid);
        String adminKey = "dc833fe6f0ef86dbcf713dead206e406";
        String cid = "TC0ONETIME";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + adminKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", cid);
        params.add("tid", tid);
        params.add("partner_order_id", partnerOrderId);
        params.add("partner_user_id", partnerUserId);
        params.add("pg_token", pgToken);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://kapi.kakao.com/v1/payment/approve",
                body,
                Map.class
            );
            // 결제 승인 성공 시 true 반환
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            // 실패 시 false 반환
            return false;
        }
    }

	public boolean payment(Map<String, Object> param) {
		int row = kakaoPayDAO.payment(param);
		return row > 0;
	}

	public boolean buy_list(Map<String, Object> param) {
		int row = kakaoPayDAO.buy_list(param);
		return row > 0;
	}
	
    public void decrementRestPeriod() {
    	kakaoPayDAO.decrementRestPeriod();
    }
	
    @Scheduled(cron = "0 0 0 * * *")
    public void dailyDecrementRestPeriod() {
    	decrementRestPeriod();
    }

}
