package com.alwriter.ContentGeneratorAgent.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = true , columnDefinition = "TEXT")
    private String summary_promt;

    public String getSummary_promt() {
        return summary_promt;
    }

    public void setSummary_promt(String summary_promt) {
        this.summary_promt = summary_promt;
    }

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String topic;

    @Column(columnDefinition = "TEXT")
    private String hook;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Platform platform;

    @Column(name = "pinned")
    private boolean pinned = false;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.DRAFT;

    private UUID currentGenerationId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getHook() {
        return hook;
    }

    public void setHook(String hook) {
        this.hook = hook;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public UUID getCurrentGenerationId() {
        return currentGenerationId;
    }

    public void setCurrentGenerationId(UUID currentGenerationId) {
        this.currentGenerationId = currentGenerationId;
    }

    public Instant getLastOpenedAt() {
        return lastOpenedAt;
    }

    public void setLastOpenedAt(Instant lastOpenedAt) {
        this.lastOpenedAt = lastOpenedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    private Instant lastOpenedAt;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }

    // getters & setters
}
