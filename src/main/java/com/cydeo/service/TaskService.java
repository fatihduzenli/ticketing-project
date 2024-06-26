package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;

import java.util.List;

public interface TaskService {
    TaskDTO getByTaskId(Long id);
    List<TaskDTO>listAllTasks();

    void save(TaskDTO dto);
    void update(TaskDTO dto);

    void delete(Long id);

    int totalNonCompletedTask(String projectCode);

    int totalCompletedTask(String projectCode);

    void deleteByProject (ProjectDTO projectDTO);

    void  completeByProject(ProjectDTO projectDTO);

    List<TaskDTO> listAllTaskByStatusIsNot (Status status);

    List<TaskDTO>listAllTaskByStatus(Status status);

    List<TaskDTO> listAllNonCompletedByAssignedEmployee(UserDTO assignedEmployee);

}
