package com.alwriter.ContentGeneratorAgent.controller;


import com.alwriter.ContentGeneratorAgent.entity.TopVoice;
import com.alwriter.ContentGeneratorAgent.service.UserService;
import com.alwriter.ContentGeneratorAgent.service.VoiceFollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/voices")
public class VoiceFollowController {

    private VoiceFollowService followService = null;
    private UserService userService;

    public VoiceFollowController(VoiceFollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<List<TopVoice>> returnAllVoice(
            @RequestParam boolean is_followed,
            @PathVariable String emailId
    ) {

        UUID userId = userService.findUser(emailId).getId();
        List<TopVoice> allVoices = followService.getAllVoice(userId , is_followed);

        return ResponseEntity.ok(allVoices);
    }

    @PostMapping("/{voiceId}/follow")
    public ResponseEntity<String> followVoice(
            @RequestParam String email,
            @PathVariable UUID voiceId) {
        UUID userId = userService.findUser(email).getId();
        followService.followVoice(userId, voiceId);

        return ResponseEntity.ok("Followed successfully");
    }
}