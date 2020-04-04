package com.example.demo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="project" )
public class Project {


    /*public Project(Long id,String projectName,String projectIdentifier,String description){
        this.setProjectName(projectName);
        this.setProjectIdentifier(projectIdentifier);
        this.setDescription(description);
        this.setId(id);

    }*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message="Project name is required")
    @Column(name = "projectname", nullable = false,unique=true)
    private String ProjectName;
    @NotBlank(message="Project identifier is required")
    @Size(min=4,max=5,message="Please use 4 to 5 characters")
    @Column(name = "projectidentifier")
    private String projectidentifier;
    @Column(name = " descript", nullable = false)
    private String description;


    public String getProjectidentifier() {
        return projectidentifier;
    }

    public void setProjectidentifier(String projectidentifier) {
        this.projectidentifier = projectidentifier;
    }

   // public List<BackLog> getBackLogLIst() {
   //     return backLogLIst;
//    }

 //   public void setBackLogLIst(List<BackLog> backLogLIst) {
 //       this.backLogLIst = backLogLIst;
   // }

  //  private List<BackLog> backLogLIst;
   // @OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="project")
  //  public BackLog getBackLog() {
  //      return backLog;
  //  }

  //  public void setBackLog(BackLog backLog) {
  //      this.backLog = backLog;
 //   }

 //   private BackLog backLog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectidentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectidentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    @Column(name = "startdate", nullable = false)
    private Date startDate;
    @Column(name = "enddate", nullable = false)
    private Date endDate;
    @Column(name = "createdat", nullable = false)
    private Date created_At;
    @Column(name = "updatedat", nullable = false)
    private Date  updated_At;


    @PrePersist
    protected void onCreate(){
        this.created_At=new Date();

    }

    protected void onUpdate(){
        this.updated_At=new Date();
    }



}
