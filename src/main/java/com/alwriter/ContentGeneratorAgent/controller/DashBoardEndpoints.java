package com.alwriter.ContentGeneratorAgent.controller;

import com.alwriter.ContentGeneratorAgent.entity.Project;
import com.alwriter.ContentGeneratorAgent.entity.User;
import com.alwriter.ContentGeneratorAgent.service.FindProjectInputFormat;
import com.alwriter.ContentGeneratorAgent.service.ProjectService;
import com.alwriter.ContentGeneratorAgent.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashBoardEndpoints {
    private final UserService userService;
    private final ProjectService projectService;

    public DashBoardEndpoints(UserService userService, ProjectService projectService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping("/projects")
    public FindProjectOutputFormat findProjects(@RequestBody FindProjectInputFormat data) {

        User user = userService.findUser(data.getEmail());

        List<Project> pinnedProjects =
                projectService.findProjectByUserId(user.getId(), true);

        List<Project> projectList =
                projectService.findProjectByUserId(user.getId(), false);

        return new FindProjectOutputFormat()
                .setPinnedProjects(pinnedProjects)
                .setProjectList(projectList);
    }

}
