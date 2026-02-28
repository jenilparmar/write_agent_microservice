//package com.alwriter.ContentGeneratorAgent.service;
//
//import com.alwriter.ContentGeneratorAgent.dto.GenerateProjectRequest;
//import com.alwriter.ContentGeneratorAgent.entity.*;
//import com.alwriter.ContentGeneratorAgent.repository.GeneratedPostRepository;
//import com.alwriter.ContentGeneratorAgent.repository.ProjectRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//@Service
//public class ProjectService {
//    private final UserService userService;
//    private final ProjectRepository projectRepo;
//    private final GeneratedPostRepository generatedPostRepo;
//
//    public ProjectService(UserService userService,
//                                    ProjectRepository projectRepo,
//                                    GeneratedPostRepository generatedPostRepo) {
//        this.userService = userService;
//        this.projectRepo = projectRepo;
//        this.generatedPostRepo = generatedPostRepo;
//    }
//    public List<Project> findProjectByUserId(UUID userId , boolean needPineed ){
//        if(needPineed) return projectRepo.findByUserIdAndPinnedTrue(userId);
//        else return projectRepo.findByUserIdOrderByUpdatedAtDesc(userId);
//    }
//
//
//    @Transactional
//    public Project createProjectWithGeneration(GenerateProjectRequest req) {
//
//        // 1️⃣ Get user
//
//        User user = userService.findUser(req.getEmail());
//
//        // 2️⃣ Create Project
//        Project project = new Project();
//        project.setUserId(user.getId());
//        project.setTitle(req.getTopic());
//        project.setTopic(req.getTopic());
//        project.setHook(req.getHook());
//        project.setPlatform(req.getPlatform());
//        project.setStatus(ProjectStatus.READY);
//
//        project = projectRepo.save(project);
//
//        // 3️⃣ Create GeneratedPost (version 1)
//        GeneratedPost post = new GeneratedPost();
//        post.setProjectId(project.getId());
//        post.setSource(GenerationSource.AI);
//        post.setPrompt(req.getTopic());
//        post.setContent(req.getGeneratedContent());
//        post.setVersion(1);
//
//        post = generatedPostRepo.save(post);
//
//        // 4️⃣ Update current_generation_id
//        project.setCurrentGenerationId(post.getId());
//        projectRepo.save(project);
//
//        return project;
//    }
//
//    // Pehle wale imports rehne dena, bas niche ka method update/add kar
//    @Transactional
//    public Project saveOrUpdateProject(GenerateProjectRequest req) {
//        User user = userService.findUser(req.getEmail());
//        Project project;
//
//        // Check if we are updating an existing project (Frontend should send Project ID if available)
//        if (req.getProjectId() != null) {
//            project = projectRepo.findById(req.getProjectId())
//                    .orElseThrow(() -> new RuntimeException("Project not found"));
//        } else {
//            // Create New Project if no ID is provided
//            project = new Project();
//            project.setUserId(user.getId());
//            project.setTopic(req.getTopic());
//            project.setPlatform(req.getPlatform());
//        }
//
//        project.setTitle(req.getTopic());
//        project.setHook(req.getHook());
//        project.setStatus(ProjectStatus.READY);
//        project = projectRepo.save(project);
//
//        // Versioning logic: Find the latest version for this project
//        int latestVersion = generatedPostRepo.findTopByProjectIdOrderByVersionDesc(project.getId())
//                .map(GeneratedPost::getVersion)
//                .orElse(0);
//
//        // Create New GeneratedPost with Incremented Version
//        GeneratedPost post = new GeneratedPost();
//        post.setProjectId(project.getId());
//        post.setSource(GenerationSource.AI);
//        post.setPrompt(req.getTopic());
//        post.setContent(req.getGeneratedContent());
//        post.setVersion(latestVersion + 1); // Har baar version badhega
//
//        post = generatedPostRepo.save(post);
//
//        // Update project's current reference to the latest post
//        project.setCurrentGenerationId(post.getId());
//        return projectRepo.save(project);
//    }
//    public void pinProject(UUID projectId, boolean pinned) {
//
//        int updated = projectRepo.updatePinnedStatus(projectId, pinned);
//
//        if (updated == 0) {
//            throw new RuntimeException("Project not found");
//        }
//    }
//
//    public List<GeneratedPost> getGeneratedPostsFromProject(UUID projectId){
//        return generatedPostRepo.findByProjectIdOrderByVersionDesc(projectId);
//    }
//
//    public Optional<Project> GetProject(UUID projectId){
//        return projectRepo.findById(projectId);
//    }
//}
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

    public List<Project> findProjectByUserId(UUID userId, boolean needPinned) {
        if (needPinned) return projectRepo.findByUserIdAndPinnedTrue(userId);
        else return projectRepo.findByUserIdOrderByUpdatedAtDesc(userId);
    }

    @Transactional
    public Project saveOrUpdateProject(GenerateProjectRequest req) {
        User user = userService.findUser(req.getEmail());
        Project project;

        // 1. Project check
        if (req.getProjectId() != null) {
            // Existing Project update
            project = projectRepo.findById(req.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
        } else {
            // 🔥 NEW PROJECT creation
            project = new Project();
            project.setUserId(user.getId());
            project.setPinned(false);
        }

        // 2. Set details
        project.setTitle(req.getTopic());
        project.setTopic(req.getTopic());
        project.setHook(req.getHook());
        project.setPlatform(req.getPlatform());
        project.setStatus(ProjectStatus.READY);

        // Summary prompt field agar tune entity mein add kiya hai toh:
        // project.setSummaryPrompt(req.getSummary_prompt());

        String sPrompt = req.getSummary_prompt() != null ? req.getSummary_prompt() : "";
        project.setSummary_promt(sPrompt);
        // Save project first to get ID
        project = projectRepo.save(project);

        // 3. Versioning Logic
        int latestVersion = generatedPostRepo.findTopByProjectIdOrderByVersionDesc(project.getId())
                .map(GeneratedPost::getVersion)
                .orElse(0);

        // 4. Create Post
        GeneratedPost post = new GeneratedPost();
        post.setProjectId(project.getId());
        post.setSource(GenerationSource.AI);
        post.setPrompt(req.getTopic());
        post.setContent(req.getGeneratedContent());
        post.setVersion(latestVersion + 1); // Auto-increment

        post = generatedPostRepo.save(post);

        // 5. Link and Final Save
        project.setCurrentGenerationId(post.getId());
        return projectRepo.save(project);
    }

    public void pinProject(UUID projectId, boolean pinned) {
        int updated = projectRepo.updatePinnedStatus(projectId, pinned);
        if (updated == 0) {
            throw new RuntimeException("Project not found");
        }
    }
}