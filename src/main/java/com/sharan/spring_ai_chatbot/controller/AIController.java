package com.sharan.spring_ai_chatbot.controller;

import com.sharan.spring_ai_chatbot.dto.AITopicResponse;
import com.sharan.spring_ai_chatbot.dto.ConversationResponse;
import com.sharan.spring_ai_chatbot.service.AIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/askAI")
    public AITopicResponse askAI(@RequestParam String question, @RequestParam String systemPrompt) {
        return
                aiService.getAIResponse(question, systemPrompt);
    }

    @GetMapping("/conversationAI")
    public ConversationResponse conversation(@RequestParam String question, @RequestParam(required = false) String conversationId) {
        return
                aiService.conversationResponse(question, conversationId);
    }

}
