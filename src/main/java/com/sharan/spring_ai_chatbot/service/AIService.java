package com.sharan.spring_ai_chatbot.service;


import com.sharan.spring_ai_chatbot.dto.AITopicResponse;
import com.sharan.spring_ai_chatbot.dto.ConversationResponse;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.memory.ChatMemory;
import org.slf4j.Logger;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Service
public class AIService {

    private final ChatClient chatClient;
    private static final Logger log = LoggerFactory.getLogger(AIService.class);
    public AIService(ChatClient.Builder chatClientBuilder) {
        MessageWindowChatMemory memory = MessageWindowChatMemory.builder().maxMessages(20).build();

        this.chatClient = chatClientBuilder.defaultSystem("Answer the question based on the provided context. " +
                        "If the question is not related to the context, answer based on your general knowledge.")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }
    public AITopicResponse getAIResponse(String question, String systemPrompt) {
        try{
            return
                    chatClient.prompt().system(systemPrompt).user(question).call()
                            .entity(AITopicResponse.class);
        }
        catch (Exception e) {
            AITopicResponse error = new AITopicResponse();
            error.setSummary("Faced Error");
            return error;
        }
    }

    public ConversationResponse conversationResponse(
            String question,
            String conversationId
    ) {
        final String id = conversationId != null ? conversationId : UUID.randomUUID().toString();

        try {

            String content = chatClient.prompt()

                    .advisors(advisorSpec ->
                            advisorSpec.param(
                                    ChatMemory.CONVERSATION_ID,
                                    id
                            )
                    )

                    .user(question)

                    .call()

                    .content();

            return new ConversationResponse(id, content);

        } catch (Exception e) {

            log.error(
                    "conversationResponse failed for conversationId={}",
                    id,
                    e
            );

            return new ConversationResponse(
                    id,
                    "Faced Error: " + e.getMessage()
            );
        }
    }

    public Flux<String> streamResponse(String question, String conversationId) {
        final String id = conversationId != null ? conversationId : UUID.randomUUID().toString();
        return chatClient.prompt()
                .advisors(advisorspec -> advisorspec.param(ChatMemory.CONVERSATION_ID, id))
                .user(question)
                .stream()
                .content();
    }
}

