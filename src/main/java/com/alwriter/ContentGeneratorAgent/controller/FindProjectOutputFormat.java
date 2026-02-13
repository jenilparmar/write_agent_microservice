package com.alwriter.ContentGeneratorAgent.controller;

import com.alwriter.ContentGeneratorAgent.entity.Project;
import java.util.List;

public class FindProjectOutputFormat {

    private List<Project> pinnedProjects;
    private List<Project> projectList;

    public List<Project> getProjectList() {
        return projectList;
    }

    public FindProjectOutputFormat setProjectList(List<Project> projectList) {
        this.projectList = projectList;
        return this;
    }

    public List<Project> getPinnedProjects() {
        return pinnedProjects;
    }

    public FindProjectOutputFormat setPinnedProjects(List<Project> pinnedProjects) {
        this.pinnedProjects = pinnedProjects;
        return this;
    }
}
