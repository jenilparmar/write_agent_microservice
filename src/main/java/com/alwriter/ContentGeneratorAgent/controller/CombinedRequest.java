package com.alwriter.ContentGeneratorAgent.controller;

import java.util.List;

public  class CombinedRequest {
    private String topic;
    private String platform;

    public CombinedRequest() {}
    private List<String> imageDescriptions;

    public List<String> getImageDescriptions() { return imageDescriptions; }
    public void setImageDescriptions(List<String> imageDescriptions) { this.imageDescriptions = imageDescriptions; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
}