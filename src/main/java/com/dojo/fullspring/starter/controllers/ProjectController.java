package com.dojo.fullspring.starter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dojo.fullspring.starter.models.Project;
import com.dojo.fullspring.starter.models.User;
import com.dojo.fullspring.starter.service.ProjectService;
import com.dojo.fullspring.starter.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class ProjectController {

	//nb: le service a pr but d'utiliser le code ecrit dedans
	// partout donc on peut l'injecter partout mêm dns l service

    // injection dpendance
    @Autowired
	private UserService userService;

    @Autowired
	private ProjectService projectService;


    // home page
    @RequestMapping("/dashboard")
	public String dashboards(HttpSession session, 
                            Model model) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId); 
		
		
	 	model.addAttribute("user", user);
		model.addAttribute("unassignedProjects", projectService.getUnassignedProjects(user)); // tous les projets qui n st pas assignès a cet utilisateur specifique
		model.addAttribute("assignedProjects", projectService.getAssignedProjects(user));  // tous les projets qui  st  assignès a cet utilisateur specifique
		 
		return "dashboard.jsp";
	} 


    // join project team
    @RequestMapping("/dashboard/join/{id}")
	public String joinTeam(@PathVariable("id") Long idProject, 
                           HttpSession session, 
                           Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
        
		Project project = projectService.findById(idProject);
		
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		
		user.getProjects().add(project);
		userService.updateUser(user);
		
		model.addAttribute("user", user);
		model.addAttribute("unassignedProjects", projectService.getUnassignedProjects(user));
		model.addAttribute("assignedProjects", projectService.getAssignedProjects(user));
		
		return "redirect:/dashboard";
	}


    // quit project team
    @RequestMapping("/dashboard/leave/{id}")
	public String leaveTeam(@PathVariable("id") Long idProject,
                            HttpSession session,
                             Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

		Project project = projectService.findById(idProject);
		
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		
		user.getProjects().remove(project);
		userService.updateUser(user);
		
		model.addAttribute("user", user);
		model.addAttribute("unassignedProjects", projectService.getUnassignedProjects(user));
		model.addAttribute("assignedProjects", projectService.getAssignedProjects(user));
		
		return "redirect:/dashboard";
	}



    // show details project
    @GetMapping("/projects/{id}")
	public String viewProject(@PathVariable("id") Long id, 
                              HttpSession session, 
                              Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		//project
		Project project = projectService.findById(id);

		model.addAttribute("project", project);

		//user
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		
		model.addAttribute("user", user);

		return "view_project.jsp";
	}


    // page create new project
    @GetMapping("/projects/new")
	public String newProject(@ModelAttribute("project") Project project, 
                             HttpSession session, 
                             Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

		Long userId = (Long) session.getAttribute("userId"); // on pass le userid qui sera passer au input
		                                                         // de type hidden qui aura la valeur du lead(one) projet(many)
																 // one to many
		
		model.addAttribute("userId", userId);
		return "new_project.jsp";
	} 


    // processing create new project
    @PostMapping("/projects/new")
	public String addNewProject(@Valid @ModelAttribute("project") Project project, 
                                BindingResult result, 
                                HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		if(result.hasErrors()) {
			return "new_project.jsp";
		}else {
			// pr l one to many
			projectService.addProject(project); // je save le project objet (one to many)
			
            // pr le many to many
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findById(userId);  // on cible l user specifique

			user.getProjects().add(project); // je l'enregistre dns la liste de projet de user

			userService.updateUser(user); // je save le user specifique
            
			return "redirect:/dashboard";
		}
	}




    // edit page project
    @GetMapping("/projects/edit/{id}")
	public String openEditProject(@PathVariable("id") Long id, 
                                   HttpSession session,
                                   Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Project project = projectService.findById(id);

		model.addAttribute("project", project);

		//il n'ont plus passer le userid pr le one to many : lead(one), projects(many) . pourquoi ?

		return "edit_project.jsp";
	}



    // processing edit projet
    @PostMapping("/projects/edit/{id}")  // changer le chemin en process_edit
	public String editProject(
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute("project") Project project, 
			BindingResult result, 
			HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		
		if(result.hasErrors()) {
            // voir si faut repasser le modelAtribute pr conserver ls infos dns le cas d'une erreur
			return "edit_project.jsp";
		}else {
			//pr le one to many
			project.setLead(user); // on change le user specifique

			// pr l many to many
			Project thisProject = projectService.findById(id); // on cible le project specific
			project.setUsers(thisProject.getUsers()); // ???
			projectService.updateProject(project); // ???
			return "redirect:/dashboard";
		}
	}

    
    
    // delete project
    @RequestMapping("/projects/delete/{id}")
	public String deleteProject(@PathVariable("id") Long id, 
                                HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Project project = projectService.findById(id); // on cibl le projet specific

		projectService.deleteProject(project); // on l supprime
		
		return "redirect:/dashboard";
	}
    
}




