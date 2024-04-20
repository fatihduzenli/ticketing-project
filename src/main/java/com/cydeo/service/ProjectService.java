package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.CrudService;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO,String> {

   void complete (ProjectDTO projectCode);
   List<ProjectDTO>getCountedListOfProjectDTO (UserDTO user);



}
