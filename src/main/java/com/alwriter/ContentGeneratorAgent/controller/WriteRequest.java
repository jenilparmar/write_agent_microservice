package com.alwriter.ContentGeneratorAgent.controller;


import java.util.ArrayList;
import java.util.List;

public class WriteRequest {

    private String topic;
    private String idea; // MUST be String
    private List<String> user_preferences;
    private String user_suggestion = "";
    private String tone = "General";
    private String write_result = "";
    private String summary_prompt = "";
    private String platform = "general";

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public void setUser_preferences(List<String> user_preferences) {
        this.user_preferences = user_preferences;
    }

    public void setUser_suggestion(String user_suggestion) {
        this.user_suggestion = user_suggestion;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public void setWrite_result(String write_result) {
        this.write_result = write_result;
    }

    public void setSummary_prompt(String summary_prompt) {
        this.summary_prompt = summary_prompt;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getIdea() {
        return idea;
    }

    public List<String> getUser_preferences() {
        return user_preferences;
    }

    public String getUser_suggestion() {
        return user_suggestion;
    }

    public String getTone() {
        return tone;
    }

    public String getWrite_result() {
        return write_result;
    }

    public String getSummary_prompt() {
        return summary_prompt;
    }

    public String getPlatform() {
        return platform;
    }
// getters & setters
}




