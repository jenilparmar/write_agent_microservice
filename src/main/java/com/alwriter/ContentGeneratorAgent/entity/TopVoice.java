package com.alwriter.ContentGeneratorAgent.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "top_voices")
public class TopVoice {
    @Id
    @GeneratedValue
    private UUID topVoiceId;

    @Column()
    private String name;
    @Column()
    private String handle;
    private String avatarUrl;

    public UUID getTopVoiceId() {
        return topVoiceId;
    }

    public void setTopVoiceId(UUID topVoiceId) {
        this.topVoiceId = topVoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
