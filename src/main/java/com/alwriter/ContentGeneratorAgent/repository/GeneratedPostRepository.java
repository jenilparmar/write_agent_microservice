package com.alwriter.ContentGeneratorAgent.repository;

import com.alwriter.ContentGeneratorAgent.entity.GeneratedPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GeneratedPostRepository extends JpaRepository<GeneratedPost, UUID> {

    // Get latest generation of a project
    Optional<GeneratedPost> findTopByProjectIdOrderByVersionDesc(UUID projectId);

    // Get full history
    List<GeneratedPost> findByProjectIdOrderByVersionDesc(UUID projectId);


}
