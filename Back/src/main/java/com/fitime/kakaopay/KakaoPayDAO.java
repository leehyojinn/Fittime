package com.fitime.kakaopay;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface KakaoPayDAO {
    void insertKakaoPayment(Map<String, Object> param);
    Map<String, Object> getKakaoPaymentByTid(@Param("tid") String tid);
    void updateKakaoPaymentStatus(@Param("tid") String tid, @Param("status") String status);
    String getOrderIdByTid(String tid);
    String getUserIdByTid(String tid);
}