package com.alwriter.ContentGeneratorAgent.controller;


public class WriteResponse {
    private String write_result;

    public void setWrite_result(String write_result) {
        this.write_result = write_result;
    }

    public void setSummary_prompt(String summary_prompt) {
        this.summary_prompt = summary_prompt;
    }

    public String getWrite_result() {
        return write_result;
    }

    public String getSummary_prompt() {
        return summary_prompt;
    }

    private String summary_prompt;

    // getters & setters
}
