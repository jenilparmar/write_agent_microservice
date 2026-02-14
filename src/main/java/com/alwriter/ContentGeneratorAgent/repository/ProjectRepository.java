package com.alwriter.ContentGeneratorAgent.repository;

import com.alwriter.ContentGeneratorAgent.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    List<Project> findByUserId(UUID userId);

    // Optional useful ones
    List<Project> findByUserIdAndPinnedTrue(UUID userId);

    List<Project> findByUserIdOrderByUpdatedAtDesc(UUID userId);

    @Modifying
    @Transactional
    @Query("""
    UPDATE Project p
    SET p.pinned = :pinned
    WHERE p.id = :projectId
""")
    int updatePinnedStatus(UUID projectId, boolean pinned);
}
