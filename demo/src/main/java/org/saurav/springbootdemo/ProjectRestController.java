package org.saurav.springbootdemo;

import org.saurav.springbootdemo.model.Project;
import org.saurav.springbootdemo.model.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest interface for the project resource
 * @author Saurav Sarkar
 *
 */
@RestController
public class ProjectRestController {
	
	@Autowired
	private ProjectRepository repository;
	
	@GetMapping("/project")
	public Iterable<Project> get() {
		return repository.findAll();
	}
	
	@GetMapping("/project/{id}")
	public Project get(@PathVariable long id) {
		return repository.findById(id);
	}
	
	
	@PostMapping("/project")
	public Long post(@RequestBody Project project) {
		Project resultProject = repository.save(project);
		return resultProject.getId();
	}
	
	@PutMapping("/project/{id}")
	public Long put(@PathVariable long id , @RequestBody Project project) {
		Project resultProject = repository.findById(id);
		
		resultProject.setName(project.getName());
		resultProject.setDescription(project.getDescription());
		
		repository.save(resultProject);
		return resultProject.getId();
		
	}
	
	@DeleteMapping("/project/{id}")
	public void delete(@PathVariable long id) {
		Project project = repository.findById(id);
		repository.delete(project);
		return;
		
	}

}
