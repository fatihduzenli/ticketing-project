package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO, Long> implements TaskService {



    @Override
    public TaskDTO save(TaskDTO task) {

        if(task.getStatus() == null)
            task.setStatus(Status.OPEN);

        if(task.getAssignedDate() == null)
            task.setAssignedDate(LocalDate.now());

        if(task.getId()==null)
            task.setId(UUID.randomUUID().getMostSignificantBits());

        return super.save(task.getId(),task);

    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaskDTO task) {
        TaskDTO foundTask=findById(task.getId());

        task.setStatus(foundTask.getStatus());
        task.setAssignedDate(foundTask.getAssignedDate());
        super.update(task.getId(), task);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }


    @Override
    public List<TaskDTO> findTaskByManager(UserDTO manager) {
        return findAll().stream()
                .filter(taskDTO -> taskDTO.getProjectDTO().getAssignedManager().equals(manager))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllTaskByStatusIsNot(Status status) {
        return findAll().stream()
                .filter(task->task.getStatus()!=Status.COMPLETE)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllTaskByStatusIsCompleted(Status status) {


        return findAll().stream()
                .filter(taskDTO -> taskDTO.getStatus()==Status.COMPLETE)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO task) {
        findById(task.getId()).setStatus(task.getStatus()); //first status is updated
        update(task);               // Second,  task is updated with the new status information
    }
}
