package com.alwriter.ContentGeneratorAgent.repository;

import com.alwriter.ContentGeneratorAgent.entity.VoiceFollow;
import com.alwriter.ContentGeneratorAgent.entity.VoiceFollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoiceFollowRepository
        extends JpaRepository<VoiceFollow, VoiceFollowId> {
    List<VoiceFollow> findByUser_Id(UUID userId);

    boolean existsByIdUserIdAndIdVoiceId(UUID userId, UUID voiceId);

    long countByIdVoiceId(UUID voiceId);
}