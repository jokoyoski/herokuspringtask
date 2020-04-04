package com.example.demo.repositories;

import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;



public interface IProjectRepository {

   String SaveProject(Project project);
   String SaveBacklog(BackLog backLog);

    Project GetProjectByName(String projectName);
    String DeleteProject(Long ProjectId);

    Collection<Project> GetAllProject();

    List<BackLog> GetBackLogByProjectId(Long projectId);




}

