package org.saurav.springbootdemo.model;

import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	Project findById(long id);

}
