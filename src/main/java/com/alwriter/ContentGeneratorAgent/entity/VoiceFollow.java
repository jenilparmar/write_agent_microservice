package com.alwriter.ContentGeneratorAgent.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "voice_follows")
public class VoiceFollow {

    @EmbeddedId
    private VoiceFollowId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("voiceId")
    @JoinColumn(name = "voice_id", nullable = false)
    private TopVoice voice;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    public VoiceFollow() {}

    public VoiceFollow(User user, TopVoice voice) {
        this.user = user;
        this.voice = voice;
        this.id = new VoiceFollowId(user.getId(), voice.getTopVoiceId());
    }

    // Getters
    public VoiceFollowId getId() { return id; }
    public User getUser() { return user; }
    public TopVoice getVoice() { return voice; }
    public Instant getCreatedAt() { return createdAt; }
}