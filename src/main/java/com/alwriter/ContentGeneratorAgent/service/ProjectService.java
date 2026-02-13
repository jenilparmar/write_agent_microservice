package com.alwriter.ContentGeneratorAgent.service;

import com.alwriter.ContentGeneratorAgent.entity.Project;
import com.alwriter.ContentGeneratorAgent.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ProjectService {
    private ProjectRepository projectrepo;
    public ProjectService(ProjectRepository projectrepo){
        this.projectrepo = projectrepo;
    }


    public List<Project> findProjectByUserId(UUID userId , boolean needPineed ){
        if(needPineed) return projectrepo.findByUserIdAndPinnedTrue(userId);
        else return projectrepo.findByUserIdOrderByUpdatedAtDesc(userId);
    }


}
