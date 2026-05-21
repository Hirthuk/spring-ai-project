package com.sharan.spring_ai_chatbot.dto;


import java.util.List;

public class AITopicResponse {

    private String topic;

    public String getDifficulty() {
        return difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public List<String> getAdvantages() {
        return advantages;
    }

    public String getSummary() {
        return summary;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setAdvantages(List<String> advantages) {
        this.advantages = advantages;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private String difficulty;
    private List<String> advantages;
    private String summary;

    public AITopicResponse() {
    }

    public AITopicResponse(String topic, String difficulty, List<String> advantages, String summary) {
        this.topic = topic;
        this.difficulty = difficulty;
        this.advantages = advantages;
        this.summary = summary;
    }


}
