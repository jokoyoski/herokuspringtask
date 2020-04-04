package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {

    @Id
    @GeneratedValue
    private Long id;
    @Column(updatable = false)
    private String projectSequence;
    @NotBlank(message="Please include a project summary")
    @Column(name = "summary", nullable = false)
    private String summary;

    public String getProjectidentifier() {
        return projectidentifier;
    }

    public void setProjectidentifier(String projectidentifier) {
        this.projectidentifier = projectidentifier;
    }

    @Column(name="projectidentifier")
    private String projectidentifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    @Column(name="acceptancecriteria")
    private String acceptanceCriteria;
    @Column(name="status")
    private String status;
    @Column(name="priority")
    private Integer priority;
    @Column(name="duedate")
    private Date dueDate;
    @Column(name="createat")
    private Date create_At;
    @Column(name="updateat")
    private Date update_at;

    @Override
    public String  toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", projectidentifier='" + projectidentifier + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", create_At=" + create_At +
                ", update_at=" + update_at +
                '}';
    }
}
