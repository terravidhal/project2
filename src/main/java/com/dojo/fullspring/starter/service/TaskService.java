package com.dojo.fullspring.starter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dojo.fullspring.starter.models.Project;
import com.dojo.fullspring.starter.models.Task;
import com.dojo.fullspring.starter.models.User;
import com.dojo.fullspring.starter.repositories.TaskRepo;



@Service
public class TaskService {
	
    private final TaskRepo taskRepo;

	public TaskService(TaskRepo taskRepo) {
		this.taskRepo = taskRepo;
	}
	
 


	public List<Task> allTasks(User user, Project project){
       
	/* 	List<Task> Taskss = taskRepo.findAll();

		List<Task> matchingTasks = new ArrayList<>(); 

		for (Task elt: Taskss) {
			if (elt.getProject() == project) {
				matchingTasks.add(elt);
			  }
		}
		return matchingTasks; */

		return taskRepo.findAll();
	}
	
	
	public Task addTask(Task task) {
		return taskRepo.save(task);
	}
	
	
	public Task findById(Long id) {
		Optional<Task> optionalTask = taskRepo.findById(id);
		if(optionalTask.isPresent()) {
			return optionalTask.get();
		}else {
			return null;
		}
	}
}
