package com.alwriter.ContentGeneratorAgent.service;

import com.alwriter.ContentGeneratorAgent.dto.GenerateProjectRequest;
import com.alwriter.ContentGeneratorAgent.entity.*;
import com.alwriter.ContentGeneratorAgent.repository.GeneratedPostRepository;
import com.alwriter.ContentGeneratorAgent.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ProjectService {
    private final UserService userService;
    private final ProjectRepository projectRepo;
    private final GeneratedPostRepository generatedPostRepo;

    public ProjectService(UserService userService,
                                    ProjectRepository projectRepo,
                                    GeneratedPostRepository generatedPostRepo) {
        this.userService = userService;
        this.projectRepo = projectRepo;
        this.generatedPostRepo = generatedPostRepo;
    }
    public List<Project> findProjectByUserId(UUID userId , boolean needPineed ){
        if(needPineed) return projectRepo.findByUserIdAndPinnedTrue(userId);
        else return projectRepo.findByUserIdOrderByUpdatedAtDesc(userId);
    }


    @Transactional
    public Project createProjectWithGeneration(GenerateProjectRequest req) {

        // 1️⃣ Get user

        User user = userService.findUser(req.getEmail());

        // 2️⃣ Create Project
        Project project = new Project();
        project.setUserId(user.getId());
        project.setTitle(req.getTopic());
        project.setTopic(req.getTopic());
        project.setHook(req.getHook());
        project.setPlatform(req.getPlatform());
        project.setStatus(ProjectStatus.READY);

        project = projectRepo.save(project);

        // 3️⃣ Create GeneratedPost (version 1)
        GeneratedPost post = new GeneratedPost();
        post.setProjectId(project.getId());
        post.setSource(GenerationSource.AI);
        post.setPrompt(req.getTopic());
        post.setContent(req.getGeneratedContent());
        post.setVersion(1);

        post = generatedPostRepo.save(post);

        // 4️⃣ Update current_generation_id
        project.setCurrentGenerationId(post.getId());
        projectRepo.save(project);

        return project;
    }

    public void pinProject(UUID projectId, boolean pinned) {

        int updated = projectRepo.updatePinnedStatus(projectId, pinned);

        if (updated == 0) {
            throw new RuntimeException("Project not found");
        }
    }
}
