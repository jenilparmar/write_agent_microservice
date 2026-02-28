package com.alwriter.ContentGeneratorAgent.dto;

import java.util.UUID;

public class ProjectFromProjectIdInputFormat {
    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    private UUID projectId;
}
