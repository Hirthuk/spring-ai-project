package com.sharan.spring_ai_chatbot.dto;

public class ConversationResponse {

    private String conversationId;
    private String content;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ConversationResponse(String conversationId, String content) {
        this.conversationId = conversationId;
        this.content = content;
    }

    public ConversationResponse() {

    }
}
