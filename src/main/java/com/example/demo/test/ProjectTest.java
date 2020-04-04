package com.example.demo.test;

import com.example.demo.domain.Project;
import com.example.demo.repositories.IProjectRepository;
import com.example.demo.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProjectTest {

  @Autowired
  ProjectService projectService;

  @Mock
  private IProjectRepository projectRepository;
  //@InjectMocks // auto inject helloRepository
//  private HelloService helloService = new HelloServiceImpl();

  String name = "sade";
 // Project project = new Project();

  @BeforeEach
  void setMockOutput() {    //you have to mock before you use this ,ethod

   ;


   // when(projectRepository.findProjectByProjectName(name)).thenReturn(getProjectByName(name));
  }

/*  @Test
  void testgetProjectByName() {

    String finalName = projectRepository.findProjectByProjectName("sade").getProjectName();

    assertNotEquals(name, finalName);
  }


  public Project getProjectByName(String name) {
    Project p = new Project();

    ArrayList<String> projectList = new ArrayList<String>();
    projectList.add("fatai");
    projectList.add("oke");
    projectList.add("tunde");
    projectList.add("bimpe");
    projectList.add("deola");
   // projectList.add("deola");

    for (String v : projectList) {

      if (v == name) {
        p.setProjectName(name);
        return p;
      }

    }

   return p;
  }*/
}



