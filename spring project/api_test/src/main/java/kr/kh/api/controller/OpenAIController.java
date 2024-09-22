package kr.kh.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kr.kh.api.model.dto.PromptDTO;

@RestController
@RequestMapping("/openai")
public class OpenAIController {

    private static final String API_KEY = "";
    private static final String API_URL = "https://api.openai.com/v1/completions";

    @PostMapping("/generate")
    public String generateText(@RequestBody PromptDTO promptRequest) {
        String prompt = promptRequest.getPrompt();
        System.out.println(prompt);
        try {
            // API 요청 보내기
            HttpResponse<kong.unirest.JsonNode> response = Unirest.post(API_URL)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .body("{"
                            + "\"model\": \"text-davinci-001\","
                            + "\"prompt\": \"" + prompt + "\","
                            + "\"max_tokens\": 100"
                            + "}")
                    .asJson();
            // 응답 상태 코드 확인
            if (response.getStatus() != 200) {
                return "Error: " + response.getStatus() + " - " + response.getStatusText();
            }

         // JSON 응답 파싱
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonResponse = mapper.readTree(response.getBody().toString());

            // 응답 구조 확인
            if (jsonResponse.has("choices") && jsonResponse.get("choices").isArray() && jsonResponse.get("choices").size() > 0) {
                String generatedText = jsonResponse.get("choices").get(0).get("text").asText();
                return generatedText;
            } else {
                return "Error: No choices returned from OpenAI API.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while generating text.";
        }
    }
}

