package org.saurav.springbootdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	private String url;
	
	public Project() {
		// TODO Auto-generated constructor stub
	}
	
	public Project(String name, String url) {
		this.name = name;
		this.url  = url;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		 return String.format(
			        "Customer[id=%d, name='%s']",
			        id, name);
	}

}
