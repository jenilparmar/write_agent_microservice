package com.alwriter.ContentGeneratorAgent.dto;

import java.util.UUID;

public class PinProjectInputFormat {
    public UUID getProject_id() {
        return project_id;
    }

    public void setProject_id(UUID project_id) {
        this.project_id = project_id;
    }

    private UUID project_id;
}
