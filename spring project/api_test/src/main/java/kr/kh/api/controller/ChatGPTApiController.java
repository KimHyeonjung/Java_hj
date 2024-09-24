package kr.kh.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.kh.api.service.ChatGPTService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatGPTApiController {

    private final ChatGPTService chatGPTService;

    @GetMapping("/api/chatGPT")
    public ResponseEntity<String> chatGPT(@RequestBody String message) {
        return chatGPTService.chat(message);
    }
}

