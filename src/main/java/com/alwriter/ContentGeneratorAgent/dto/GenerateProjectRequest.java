package com.alwriter.ContentGeneratorAgent.dto;

import com.alwriter.ContentGeneratorAgent.entity.Platform;

import java.util.UUID;

public class GenerateProjectRequest {

    private String email;
    private String topic;
    private String hook;
    private Platform platform;
    private String generatedContent;
    private UUID projectId;
    private String summary_prompt;

    public String getSummary_prompt() {
        return summary_prompt;
    }

    public void setSummary_prompt(String summary_prompt) {
        this.summary_prompt = summary_prompt;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getHook() {
        return hook;
    }

    public void setHook(String hook) {
        this.hook = hook;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getGeneratedContent() {
        return generatedContent;
    }

    public void setGeneratedContent(String generatedContent) {
        this.generatedContent = generatedContent;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    private int version;

    // getters & setters
}
