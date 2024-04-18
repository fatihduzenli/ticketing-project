package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
   private Long id;

   private ProjectDTO projectDTO;
   private UserDTO employee;
   private String taskSubject;
   private String taskDetail;
   private LocalDate assignedDate;
   private Status status;
}
