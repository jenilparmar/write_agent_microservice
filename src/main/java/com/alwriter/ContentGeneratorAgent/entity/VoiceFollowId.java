package com.alwriter.ContentGeneratorAgent.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class VoiceFollowId implements Serializable {

        private UUID userId;
        private UUID voiceId;

        public VoiceFollowId() {}

    public VoiceFollowId(UUID userId, UUID voiceId) {
        this.userId = userId;
        this.voiceId = voiceId;
    }

    // equals & hashCode (MANDATORY)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoiceFollowId that)) return false;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(voiceId, that.voiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, voiceId);
    }
}