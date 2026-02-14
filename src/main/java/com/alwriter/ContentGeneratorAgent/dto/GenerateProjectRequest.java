package com.alwriter.ContentGeneratorAgent.dto;

import com.alwriter.ContentGeneratorAgent.entity.Platform;

public class GenerateProjectRequest {

    private String email;
    private String topic;
    private String hook;
    private Platform platform;
    private String generatedContent;

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
