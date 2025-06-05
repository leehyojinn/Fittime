package com.fitime.kakaopay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fitime.reservation.ReservationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/kakaopay")
@CrossOrigin
public class KakaoPayController {

    @Autowired
    private KakaoPayService kakaoPayService;
    @Autowired
    private ReservationService reservationservice;
    
	Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/ready")
    public Map<String, Object> kakaoPayReady(@RequestBody Map<String, Object> param) {
        String userId = (String) param.get("user_id");
        int productIdx = ((Number)param.get("product_idx")).intValue();
        String productName = (String) param.get("item_name");
        int price = ((Number)param.get("total_amount")).intValue();

        Map<String, Object> result = kakaoPayService.kakaoPayReady(userId, productIdx, productName, price);
        return result;
    }

    @GetMapping("/status")
    public Map<String, Object> kakaoPayStatus(@RequestParam String tid) {
        Map<String, Object> payment = kakaoPayService.getKakaoPaymentByTid(tid);
        Map<String, Object> result = new HashMap<>();
        result.put("status", payment != null ? payment.get("status") : "NOT_FOUND");
        return result;
    }

    @PostMapping("/success")
    public ResponseEntity<String> kakaoPaySuccess(
        @RequestParam String tid,
        @RequestParam String pg_token,
        @RequestBody Map<String, Object> param
    ) {
        boolean approved = kakaoPayService.approveKakaoPayment(tid, pg_token);
        if (approved) {
            kakaoPayService.updateKakaoPaymentStatus(tid, "성공");
            boolean booked = reservationservice.booking(param);
            if (booked) {
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 실패");
            }
        } else {
            kakaoPayService.updateKakaoPaymentStatus(tid, "실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }


    @GetMapping("/fail")
    public String kakaoPayFail(@RequestParam String tid) {
        kakaoPayService.updateKakaoPaymentStatus(tid, "실패");
        return "fail";
    }

    @GetMapping("/cancel")
    public String kakaoPayCancel(@RequestParam String tid) {
        kakaoPayService.updateKakaoPaymentStatus(tid, "취소");
        return "cancel";
    }

}
