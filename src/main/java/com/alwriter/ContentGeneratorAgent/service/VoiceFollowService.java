package com.alwriter.ContentGeneratorAgent.service;

import com.alwriter.ContentGeneratorAgent.entity.*;
import com.alwriter.ContentGeneratorAgent.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class VoiceFollowService {

    private final UserRepository userRepository;
    private final TopVoiceRepository voiceRepository;
    private final VoiceFollowRepository followRepository;

    public VoiceFollowService(UserRepository userRepository,
                              TopVoiceRepository voiceRepository,
                              VoiceFollowRepository followRepository) {
        this.userRepository = userRepository;
        this.voiceRepository = voiceRepository;
        this.followRepository = followRepository;
    }

    public List<TopVoice> getAllVoice(UUID userId, boolean is_followed) {
        if (is_followed) return voiceRepository.findAll()
                .stream()
                .filter(voice ->
                        followRepository.existsByIdUserIdAndIdVoiceId(
                                userId,
                                voice.getTopVoiceId()
                        )
                )
                .toList();
        else return voiceRepository.findAll()
                .stream()
                .filter(voice ->
                        !followRepository.existsByIdUserIdAndIdVoiceId(
                                userId,
                                voice.getTopVoiceId()
                        )
                )
                .toList();
    }

    @Transactional
    public void followVoice(UUID userId, UUID voiceId) {

        // Prevent duplicate follow
        if (followRepository.existsByIdUserIdAndIdVoiceId(userId, voiceId)) {
            throw new RuntimeException("Already following this voice");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TopVoice voice = voiceRepository.findById(voiceId)
                .orElseThrow(() -> new RuntimeException("Voice not found"));

        VoiceFollow follow = new VoiceFollow(user, voice);

        followRepository.save(follow);
    }
}