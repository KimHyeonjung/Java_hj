package kr.kh.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kh.spring.service.OpenAiService;

@RestController
@RequestMapping("/api/openai")
public class OpenAiController {

    @Autowired
    private OpenAiService openAiService;

    @PostMapping(value = "/prompt", produces = "application/json; charset=UTF-8")
    public String getOpenAiResponse(@RequestBody String prompt) {
        try {
            return openAiService.getResponse(prompt);
        } catch (IOException e) {
            e.printStackTrace();
            return "OpenAI API 호출 중 오류가 발생했습니다.";
        }
    }
}
