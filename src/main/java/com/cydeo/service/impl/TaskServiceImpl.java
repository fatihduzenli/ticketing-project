package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    private final ProjectMapper projectMapper;

    public TaskServiceImpl(TaskMapper taskMapper, TaskRepository taskRepository, ProjectMapper projectMapper) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public TaskDTO getByTaskId(Long id) {

        return taskMapper.convertToDto(taskRepository.getById(id));
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO dto) {

        dto.setStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        taskRepository.save(taskMapper.convertToEntity(dto));
    }

    @Override
    public void update(TaskDTO dto) {
        Task task = taskRepository.getById(dto.getId());
        Task convertedTask = taskMapper.convertToEntity(dto);
      //  convertedTask.setId(task.getId());
        convertedTask.setAssignedDate(task.getAssignedDate());
        convertedTask.setTaskStatus(task.getTaskStatus());
        taskRepository.save(convertedTask);

    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.getById(id);
        task.setIsDeleted(true);
        taskRepository.save(task);
    }

    @Override
    public int totalNonCompletedTask(String projectCode) {
        return taskRepository.totalNonCompletedTask(projectCode);
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return taskRepository.totalCompletedTask(projectCode);
    }

    @Override
    public void deleteByProject(ProjectDTO projectDTO) {
        // Here we are going the deleting all the task that was belonged to the deleted project
        Project project=projectMapper.convertToEntity(projectDTO);
        //Here we got a list of the task that belong to project that we deleted
        List<Task> task= taskRepository.findAllByProject(project);
        // Here we use the delete method that we use above which sets setIsDeleted(true)
        // We set one by one all the task's setIsDeleted field true by delete() method
        task.forEach(task1 -> delete(task1.getId()));
    }
}
