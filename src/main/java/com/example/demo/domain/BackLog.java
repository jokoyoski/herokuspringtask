package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BackLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    @Column(name="ptsequence")
    private Integer PTSequence=0;

    @Column(name="projectidentifier")
    private String projectIdentifier;



    @Column(name="projectid")
    private Long projectId;

   // public Project getProject() {
    //    return project;
  //  }

   // public void setProject(Project project) {
   //     this.project = project;
  //  }

    //@OneToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name="project_id",nullable = false)
   // @JsonIgnore
  //  private Project project;

    //main
}

