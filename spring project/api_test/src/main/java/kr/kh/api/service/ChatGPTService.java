package kr.kh.api.service;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatGPTService {

    // RestTemplate 주입받음
    private final RestTemplate restTemplate;

    // 메시지 입력받고 챗gpt의 응답을 리턴하는 메서드
    public ResponseEntity<String> chat(String message) {

        HttpHeaders headers = new HttpHeaders(); // HTTP 헤더 생성
        headers.setContentType(MediaType.APPLICATION_JSON); // 요청 본문 타입 설정
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // 수신할 응답 타입 설정
        String apiKey = ""; // 발급받은 API 키 설정 (여기서는 지움)
        headers.set("Authorization", "Bearer " + apiKey); // 인증 헤더에 API 키 추가

        JSONObject messageSystem = new JSONObject(); // 시스템 메시지 JSON 객체 생성
        messageSystem.put("role", "system");  // 역할 설정
        messageSystem.put("content", "너는 스프링 챗지피티 프로젝트 도우미야. 모든 답변은 간단한 자기소개 후에 해줘."); // 시스템 메시지 추가

        JSONObject messageUser = new JSONObject(); // 사용자 메시지 JSON 객체 생성
        messageUser.put("role", "user"); // 역할 설정
        messageUser.put("content", message); // 사용자 메시지 추가

        JSONObject requestBody = new JSONObject(); // 요청 본문을 위한 JSON 객체 생성
        requestBody.put("model", "gpt-4o-2024-05-13"); // 사용할 모델 설정
        requestBody.put("messages", new JSONArray(Arrays.asList(messageSystem, messageUser))); // 메시지 배열 추가

        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers); // HTTP 요청 엔티티 생성

        String apiEndpoint = "https://api.openai.com/v1/chat/completions"; // API 엔드포인트 설정
        try {
            // REST API 호출을 통해 응답 받기
            ResponseEntity<String> response = restTemplate.postForEntity(apiEndpoint, request, String.class);

            // 응답 상태 코드 확인
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response;  // 성공적인 응답 반환
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("api 호출 중 오류 발생!"); // 오류 메시지 반환
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("api 호출 중 예외 발생: " + e.getMessage()); // 예외 메시지 반환
        }
    }
}
