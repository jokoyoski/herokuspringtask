package com.example.demo.web;

import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;
import com.example.demo.exception.DbException;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.ProjectIdException;
import com.example.demo.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {


    @Autowired
    private ProjectService projectService;


    @PostMapping("")
    public ResponseEntity<?> CreateProject(@Valid @RequestBody Project project, BindingResult result, Principal principal) {

      var value=  principal.getName();
        if (result.hasErrors()) {
            return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);

        }
        try {
            var projectinfo = this.projectService.getProjectByProjectName(project);
            if (projectinfo.getProjectName()!=null) {
                return new ResponseEntity<String>("Duplicated  value exist", HttpStatus.BAD_REQUEST);
            }

            Integer projectCount = this.projectService.saveProject(project);
            if (projectCount < 1) {


                throw new DbException("Error occured while trying to save your info");
            }


        } catch (Exception e) {
            throw new ProjectIdException("Error occured while trying to save your info");
        }

        return new ResponseEntity<Project>(project, HttpStatus.CREATED);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteProject(@PathVariable  Long id){

        this.projectService.DeleteProjectById(id);

        return  new ResponseEntity<String>("Project Deleted Successfully", HttpStatus.OK);


    }


   @GetMapping("")
    public ResponseEntity<?> GetProject(){


        Collection<Project> projects=this.projectService.getAllProject();

        return  new ResponseEntity<Collection<Project>>(projects, HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetBackLogByProjectId(@PathVariable Long id){


        Collection<BackLog> backLogs=this.projectService.getBackLogByProjectId(id);

        return  new ResponseEntity<Collection<BackLog>>(backLogs, HttpStatus.OK);


    }






/*    @PutMapping("")
    public ResponseEntity<?> UdateProject(@Valid @RequestBody Project project , BindingResult result){

        boolean isExist=projectService.getProjectbyProjectName(project.getProjectName());
        if(isExist==true){
            throw new DuplicateRecordException("Project ID "+project.getProjectName()+" already exist");
        }
          projectService.UpdateProjectById(project);

        return  new ResponseEntity<Project>(project, HttpStatus.OK);


    } */


}
