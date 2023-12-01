package com.dojo.fullspring.starter.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.dojo.fullspring.starter.models.Project;
import com.dojo.fullspring.starter.models.Task;
import com.dojo.fullspring.starter.models.User;
import com.dojo.fullspring.starter.service.ProjectService;
import com.dojo.fullspring.starter.service.TaskService;
import com.dojo.fullspring.starter.service.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class TaskController {

    @Autowired
	private TaskService taskService;

    @Autowired
	private UserService userService;

    @Autowired
	private ProjectService projectService;


     // Tasks Methods:
    //New Task Get Method:
    @GetMapping("/projects/{projectId}/tasks")
    public String task(@ModelAttribute("taskObj") Task task ,
                       HttpSession session, 
                       @PathVariable("projectId") Long id, 
                       Model model) {
    	
    	if (session.getAttribute("userId")!= null){
    
    		Project project = projectService.findById(id);

    		model.addAttribute("project", project);
            
            
           /*     Long userId = (Long) session.getAttribute("userId");
		    User user = userService.findById(userId); 
    		 
            
            model.addAttribute("allTasks", taskService.allTasks(user, project)); */

    		return "Tasks.jsp";
    	}
    	return "redirect:/";
    }
    
    @PostMapping("/projects/{projectId}/tasks")
    public String addTask(@ModelAttribute("taskObj") Task task ,
                            HttpSession session, 
                           @PathVariable("projectId") Long id,
    						Model model) {

    	Long userId = (Long) session.getAttribute("userId");
    	User user = userService.findById(userId);

		Project project = projectService.findById(id);
		
        
		task.setAddedBy(user);
        task.setProject(project);
        
        taskService.addTask(task);
		
		return "redirect:/projects/"+id+"/tasks";
    }

}
