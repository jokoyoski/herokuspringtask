package com.example.demo.impl;

import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;
import com.example.demo.repositories.IProjectRepository;
import com.example.demo.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepository implements IProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GetProjectByProjectName getProjectByProjectName;
    @Autowired
    private InsertProject insertProject;

    @Autowired
    private InsertBackLog insertBackLog;

    @Autowired
    private DeleteProject deleteProject;

    @Autowired
    private GetProjectList projectList;

    @Autowired
    private  GetBackLogByProjectId getBackLogByProjectId;
    @Override
    public String SaveProject(Project project) {

        String value=insertProject.InsertProject(project);
        return value;

    }

    @Override
    public String SaveBacklog(BackLog backLog) {
        return insertBackLog.InsertBackLog(backLog);
    }


    @Override
    public Project GetProjectByName(String projectName) {

       var projectValue= getProjectByProjectName.findProjectByProjectName(projectName);

       return projectValue;
    }

    @Override
    public String DeleteProject(Long ProjectId) {

        return deleteProject.DeleteProject(ProjectId);
    }

    @Override
    public Collection<Project> GetAllProject() {
        return projectList.GetProject();
    }

    @Override
    public List<BackLog> GetBackLogByProjectId(Long projectId) {
      return getBackLogByProjectId.GetBackLogByProjectId((projectId));
    }


}
