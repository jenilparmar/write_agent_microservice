package com.alwriter.ContentGeneratorAgent.repository;

import com.alwriter.ContentGeneratorAgent.entity.GeneratedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GeneratedPostRepository extends JpaRepository<GeneratedPost, UUID> {

    // Get latest generation of a project
    Optional<GeneratedPost> findTopByProjectIdOrderByVersionDesc(UUID projectId);

    // Get full history
    List<GeneratedPost> findByProjectIdOrderByVersionDesc(UUID projectId);

    @Query("SELECT MAX(g.version) FROM GeneratedPost g WHERE g.projectId = :projectId")
    Integer findMaxVersionByProjectId(@Param("projectId") UUID projectId);
}
