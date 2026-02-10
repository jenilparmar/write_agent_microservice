package com.alwriter.ContentGeneratorAgent.controller;

public  class CombinedRequest {
    private String topic;
    private String platform;

    public CombinedRequest() {}

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
}