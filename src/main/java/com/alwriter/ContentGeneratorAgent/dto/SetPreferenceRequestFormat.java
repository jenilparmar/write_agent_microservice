package com.alwriter.ContentGeneratorAgent.dto;

public class SetPreferenceRequestFormat {
    private String email;

    public String getPreferenceString() {
        return preferenceString;
    }

    public void setPreferenceString(String preferenceString) {
        this.preferenceString = preferenceString;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private  String preferenceString;
}
