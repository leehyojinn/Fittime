package com.fitime.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.util.Jwt;
@CrossOrigin(
	    origins = "http://192.168.0.114:3000",
	    allowedHeaders = "*",
	    allowCredentials = "true"
	)
@RestController
public class MemberController {
	
    @Value("${spring.mail.username}") private String emailId;

    @Value("${spring.mail.password}") private String emailPw;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberService service;
	Map<String, Object> result = null;
	
	@GetMapping(value="/")
	public String main() {
		return "테스트";
	}
	
	// 회원가입
	@PostMapping(value="/join")
	public Map<String, Object> join(@RequestBody Map<String, String> params){
		logger.info("가입 정보 : {}",params);
		result = new HashMap<String, Object>();
		boolean success = service.join(params);
		result.put("success", success);
		return result;
	}
	
	// 로그인
	@PostMapping(value="/login")
	public Map<String, Object> login(@RequestBody Map<String, String> params){
		logger.info("로그인 정보 : "+params);
		result = new HashMap<String, Object>();
		boolean success = service.login(params);
		boolean login = false;
		String loginId = params.get("user_id");
		if(!loginId.equals("") && loginId!=null) {
			int user_level = service.get_level(params);
			if(user_level == 3) {
				int exercise_level =service.get_exerciseLevel(params.get("user_id"));
				result.put("exercise_level", exercise_level);
			}
			String token = Jwt.setToken("user_id",params.get("user_id"));
			result.put("token", token);
			result.put("user_level", user_level);
			result.put("success", success);
			login = true;
		}
		result.put("login", login);
		return result;
	}
	
	// 아이디 중복체크
	@PostMapping(value="/overlay/id")
	public Map<String, Object> overlayId(@RequestBody Map<String, Object> param){
		logger.info("아이디 중복 체크 : "+param);
		result = new HashMap<String, Object>();
		boolean success = service.overlayId(param);
		result.put("use", success);		
		return result;
	}
	
	// 이메일 중복체크
	@PostMapping(value="/overlay/email")
	public Map<String, Object> overlayEmail(@RequestBody Map<String, Object> param){
		logger.info("이메일 중복 체크 : "+param);
		result = new HashMap<String, Object>();
		boolean success = service.overlayEmail(param);
		result.put("use", success);
		return result;
	}
	
	// 회원탈퇴
	@PostMapping(value="/unregiste")
	public Map<String, Object> unregiste(@RequestBody Map<String, Object> param){
		logger.info("회원 탈퇴 : "+param);
		result = new HashMap<String, Object>();
		boolean success = service.unregiste(param);
		result.put("success", success);
		return result;
	}
	
	// 아이디 찾기
	@PostMapping(value="/find/id")
	public Map<String, Object> findId(@RequestBody Map<String, Object> param){
		logger.info("아이디 찾기 요청 : "+param);
		result = new HashMap<String, Object>();
		String email = (String) param.get("email");
		String userId = service.findId(email);
		if(userId != null && !userId.equals("")) {
			result.put("success", true);
			result.put("user_id", userId);
		}else{
			result.put("success", false);
		}
		return result;
	}
	
	// 비밀번호 찾기
	@PostMapping(value="/find/password")
	public Map<String, Object> findPw(@RequestBody Map<String, Object> param){
		logger.info("패스워드 찾기 요청 : "+param); result = new HashMap<String, Object>();
		String userPw = service.findPw(param); result.put("success", true);
		result.put("password", userPw); return result;
	}
	

	@PostMapping("/emailSend/{user_id}")
	private Map<String, Object> sendMail(@PathVariable String user_id, @RequestBody Map<String, Object> param) {
	    logger.info("메일 발송");

	    Map<String, Object> result = new HashMap<>();

	    // 1. user_id로 이메일 조회
	    String email = (String) param.get("email");
	    String receiverId = service.findEmailByUserId(user_id);
	    logger.info("이메일 : {}",email);
	    logger.info("DB 이메일 : {}",receiverId);
	    if (receiverId == null || email == null || !email.equalsIgnoreCase(receiverId)) {
	        logger.info("해당 유저의 이메일이 없습니다.");
	        result.put("success", false);
	        result.put("msg", "해당 유저의 이메일이 없습니다.");
	        return result;
	    }

	    String subject = "임시 비밀번호 발급";
	    Random random = new Random();
	    StringBuilder authenticationCode = new StringBuilder();

	    for (int i = 0; i < 6; i++) {
	        authenticationCode.append(random.nextInt(10));
	    }
	    String tempPwd = authenticationCode.toString();

	    // 3. DB에 임시 비밀번호로 변경
	    boolean updateResult = service.updateUserPassword(user_id, tempPwd);
	    if (!updateResult) {
	        logger.info("비밀번호 변경 실패");
	        result.put("success", false);
	        result.put("msg", "비밀번호 변경 실패");
	        return result;
	    }

	    // 4. 메일 발송
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	    Session session = Session.getInstance(props, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(emailId, emailPw);
	        }
	    });

	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(emailId));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverId));
	        message.setSubject(subject);
	        message.setText("임시 비밀번호: " + tempPwd);

	        Transport.send(message);
	        result.put("success", true);
	        result.put("msg", "임시 비밀번호가 이메일로 발송되었습니다.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.info("이메일 전송 실패");
	        result.put("success", false);
	        result.put("msg", "이메일 전송 실패");
	    }
	    return result;
	}


}
