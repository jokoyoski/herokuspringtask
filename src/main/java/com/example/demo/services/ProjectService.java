package com.example.demo.services;


import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;
import com.example.demo.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private IProjectRepository iProjectRepository;




    public int saveProject(Project project) {

        String projects = iProjectRepository.SaveProject(project);
        if (projects=="") {
            var projectinfo = this.iProjectRepository.GetProjectByName(project.getProjectName());
                BackLog backLog = new BackLog();
                backLog.setProjectId(projectinfo.getId());
                backLog.setProjectIdentifier(projectinfo.getProjectIdentifier());
                iProjectRepository.SaveBacklog(backLog);
        } else {
            return 0;
        }
        return 1;
    }


    public Project getProjectByProjectName(Project project) {

        var projectValue = iProjectRepository.GetProjectByName(project.getProjectName());

        return projectValue;

    }

    public void DeleteProjectById(Long id){

        iProjectRepository.DeleteProject(id);

    }


   public Collection<Project>getAllProject() {

       Collection<Project> projects = iProjectRepository.GetAllProject();
       return projects;

   }
    public Collection<BackLog>getBackLogByProjectId(Long projectId) {

        Collection<BackLog> backLogs = iProjectRepository.GetBackLogByProjectId(projectId);
        return backLogs;

    }
    }









   /* @Transactional
    public void UpdateProjectById(Project project){

        Project p=new Project();


        iProjectRepository.UpdateByProjectId(project.getProjectName(),project.getProjectIdentifier(),project.getDescription(),project.getStartDate(),project.getEndDate(),project.getUpdated_At(),
                project.getCreated_At(),project.getId());

    }


*/




