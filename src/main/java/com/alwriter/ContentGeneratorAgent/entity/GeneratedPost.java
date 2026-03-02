package com.alwriter.ContentGeneratorAgent.entity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "generated_posts")
public class GeneratedPost {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "image_hash", columnDefinition = "TEXT")
        private String imageHash;

    public String getImage_hash() {

        return this.imageHash;
    }

    public void setImage_hash(String image_hash) {
        this.imageHash = image_hash;
    }

    @Column(nullable = false)
    private UUID projectId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public GenerationSource getSource() {
        return source;
    }

    public void setSource(GenerationSource source) {
        this.source = source;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenerationSource source;   // AI or HUMAN

    @Column(columnDefinition = "TEXT")
    private String prompt;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private int version;

    private boolean isActive = true; // for soft-hide from history

    private Instant createdAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
    }

    // getters & setters
}
