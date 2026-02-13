package com.alwriter.ContentGeneratorAgent.repository;

import com.alwriter.ContentGeneratorAgent.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    List<Project> findByUserId(UUID userId);

    // Optional useful ones
    List<Project> findByUserIdAndPinnedTrue(UUID userId);

    List<Project> findByUserIdOrderByUpdatedAtDesc(UUID userId);

}
