package com.example.demo.exception;


public class ProjectIdExceptionResponse {
    public String getProjectidentifier() {
        return projectidentifier;
    }

    public void setProjectidentifier(String projectidentifier) {
        this.projectidentifier = projectidentifier;
    }

    private String projectidentifier;

    public ProjectIdExceptionResponse(String projectidentifier){
        this.projectidentifier=projectidentifier;
    }


}
