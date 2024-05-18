package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;

import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }


    @GetMapping("/create")
    public String productCreate(Model model) {

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.findManagers("Manager"));
        model.addAttribute("projects", projectService.listAllProjects());

        return "project/create";
    }

//    @PostMapping("/create")
//    public String saveProject(@ModelAttribute("project") ProjectDTO projectDTO) {
//
//        projectDTO.setStatus(Status.OPEN);
//
//        projectService.save(projectDTO);
//
//        return "redirectedirect:/project/create";
//    }
//
//
//    @GetMapping("delete/{projectCode}")
//    public String deleting(@PathVariable("projectCode") String code) {
//
//        projectService.deleteById(code);
//        return "redirect:/project/create";
//    }
//    @GetMapping("/complete/{projectCode}")
//    public String completeProject(@PathVariable("projectCode") String projectCode) {
//        projectService.complete(projectService.findById(projectCode));
//        return "redirect:/project/create";
//    }
//
//        @GetMapping("update/{projectCode}")
//        public String updateProject(@PathVariable("projectCode") String projectCode, Model model) {
//
//            model.addAttribute("project", projectService.findById(projectCode));
//            model.addAttribute("managers", userService.findManagers());
//            model.addAttribute("projects", projectService.findAll());
//
//
//            return "project/update";
//    }
//
//    @PostMapping("/update")
//    public String editProject(@ModelAttribute("project") ProjectDTO projectDTO) {
//
//        projectService.update(projectDTO);
//
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/manager/complete/{projectCode}")
//    public String managerCompleteProject(@PathVariable("projectCode") String projectCode) {
//
//        projectService.complete(projectService.findById(projectCode));
//
//        return "redirect:/project/manager/project-status";
//    }
//
//    @GetMapping("/manager/project-status")
//    public String getProjectByManager(Model model) {
//
//        UserDTO manager = userService.findById("john@cydeo.com");
//        List<ProjectDTO> projects=projectService.getCountedListOfProjectDTO(manager);
//
//        model.addAttribute("projects", projects);
//
//
//        return "/manager/project-status";
//    }
}
