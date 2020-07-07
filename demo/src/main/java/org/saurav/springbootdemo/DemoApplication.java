package org.saurav.springbootdemo;

import java.util.List;

import org.saurav.springbootdemo.model.Project;
import org.saurav.springbootdemo.model.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	
	 private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	 @Bean
	  public CommandLineRunner demo(ProjectRepository repository) {
	    return (args) -> {
	    	 List<Project> projects =  (List<Project>) repository.findAll();
	    	 if(projects.size() > 0) {
	    		 return;
	    	 }
	      // save a few customers
	      repository.save(new Project("Jack", "Bauer"));
	      repository.save(new Project("Chloe", "O'Brian"));
	      repository.save(new Project("Kim", "Bauer"));
	      repository.save(new Project("David", "Palmer"));
	      repository.save(new Project("Michelle", "Dessler"));

	      // fetch all customers
	      log.info("Customers found with findAll():");
	      log.info("-------------------------------");
	      for (Project customer : projects) {
	        log.info(customer.toString());
	      }
	      log.info("");

	      // fetch an individual customer by ID
	      Project customer = repository.findById(1L);
	      log.info("Customer found with findById(1L):");
	      log.info("--------------------------------");
	      log.info(customer.toString());
	      log.info("");

	    
	      log.info("");
	    };
	  }

}
