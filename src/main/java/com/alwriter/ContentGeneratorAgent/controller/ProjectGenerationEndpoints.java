package com.alwriter.ContentGeneratorAgent.controller;

import com.alwriter.ContentGeneratorAgent.dto.GenerateProjectRequest;
import com.alwriter.ContentGeneratorAgent.dto.ProjectFromProjectIdInputFormat;
import com.alwriter.ContentGeneratorAgent.entity.*;
import com.alwriter.ContentGeneratorAgent.repository.GeneratedPostRepository;
import com.alwriter.ContentGeneratorAgent.repository.ProjectRepository;
import com.alwriter.ContentGeneratorAgent.service.ProjectService;
import com.alwriter.ContentGeneratorAgent.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectGenerationEndpoints {

    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final GeneratedPostRepository generatedPostRepository;
    private final ProjectService projectService;
    public ProjectGenerationEndpoints(UserService userService,
                                      ProjectRepository projectRepository,
                                      GeneratedPostRepository generatedPostRepository,
                                      ProjectService projectService) {
        this.userService = userService;
        this.projectRepository = projectRepository;
        this.generatedPostRepository = generatedPostRepository;
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<List<GeneratedPost>> getProjectFromProjectId(@RequestBody ProjectFromProjectIdInputFormat data) {
        List<GeneratedPost> dataToSend = generatedPostRepository.findByProjectIdOrderByVersionDesc(data.getProjectId());
        return ResponseEntity.ok(dataToSend);
    }

//    @PostMapping("/addNewGeneration")
//    @Transactional
//    public ResponseEntity<Project> addNewGeneration(@RequestBody ProjectFromProjectIdInputFormat data){
//        GeneratedPost post = new GeneratedPost();
//        post.setProjectId(data.getProjectId());
//        post.setSource(GenerationSource.AI);
//
//        post.setPrompt(request.getTopic());
//        post.setContent(request.getGeneratedContent());
//        post.setVersion(request.getVersion());
//
//        post = generatedPostRepository.save(post);
//
//        // 4️⃣ Link current generation
//        project.setCurrentGenerationId(post.getId());
//        projectRepository.save(project);
//    }

//    @PostMapping("/generate")
//    @Transactional
//    public Project createProject(
//            @RequestBody GenerateProjectRequest request
//    ) {
//
//        // 1️⃣ Find user
//        User user = userService.findUser(request.getEmail());
//
//        // 2️⃣ Create new Project
//        Project project = new Project();
//        project.setUserId(user.getId());
//        project.setTitle(request.getTopic());
//        project.setTopic(request.getTopic());
//        project.setHook(request.getHook());
//        project.setPlatform(request.getPlatform());
//        project.setStatus(ProjectStatus.READY);
//        project.setSummary_promt(request.getSummary_prompt());
//        project = projectRepository.save(project);
//
//        // 3️⃣ Create first GeneratedPost (version = 1)
//        GeneratedPost post = new GeneratedPost();
//        post.setProjectId(project.getId());
//        post.setSource(GenerationSource.AI);
//        post.setPrompt(request.getTopic());
//        post.setContent(request.getGeneratedContent());
//        post.setVersion(request.getVersion());
//
//        post = generatedPostRepository.save(post);
//
//        // 4️⃣ Link current generation
//        project.setCurrentGenerationId(post.getId());
//        projectRepository.save(project);
//
//        return project;
//    }

    @PostMapping("/generate")
    public Project createProjectWithGeneration(@RequestBody GenerateProjectRequest request) {
        // Calling the updated service method that handles both new and existing projects
        return projectService.saveOrUpdateProject(request);
    }

}
