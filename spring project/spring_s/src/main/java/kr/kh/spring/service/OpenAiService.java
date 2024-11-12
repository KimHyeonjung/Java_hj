package kr.kh.spring.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class OpenAiService {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private String apiKey = "";

    public String getResponse(String prompt) throws IOException {
        OkHttpClient client = new OkHttpClient();
        System.out.println("프롬프트 :" + prompt);
        // OpenAI Chat API의 메시지 포맷에 맞게 JSON 데이터 구성
        JSONObject json = new JSONObject();
        json.put("model", "gpt-4o-2024-05-13");  // gpt-4 모델 사용
        json.put("temperature", 0.7);

        // messages 배열 생성
        JSONArray messages = new JSONArray();
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.put(userMessage);

        json.put("messages", messages);

        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBodyString = new String(response.body().bytes(), StandardCharsets.UTF_8);
            JSONObject responseBody = new JSONObject(responseBodyString);
            System.out.println(responseBody);
            return responseBody.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content")
                    .trim();
        }
    }
}

