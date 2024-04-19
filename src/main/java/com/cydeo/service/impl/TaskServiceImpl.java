package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO, Long> implements TaskService {

    @Override
    public TaskDTO save(TaskDTO user) {

        if (user.getAssignedDate()==null){
            user.setAssignedDate(LocalDate.now());
        }
        if (user.getStatus()==null){
            user.setStatus(Status.OPEN);
        }

        if (user.getId()==null){
            user.setId(UUID.randomUUID().getLeastSignificantBits());
        }
        return super.save(user.getId(), user);
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
    public void update(TaskDTO user) {
        if (user.getAssignedDate()==null){
            user.setAssignedDate(LocalDate.now());
        }
        if (user.getStatus()==null){
            user.setStatus(Status.OPEN);
        }

        if (user.getId()==null){
            user.setId(UUID.randomUUID().getLeastSignificantBits());
        }
        super.update(user.getId(), user);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
