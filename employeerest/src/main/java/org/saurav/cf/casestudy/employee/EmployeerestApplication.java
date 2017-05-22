package org.saurav.cf.casestudy.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class EmployeerestApplication extends SpringBootServletInitializer{
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		 	
	        return application.sources(EmployeerestApplication.class);
	    }


	public static void main(String[] args) {
		SpringApplication.run(EmployeerestApplication.class, args);
		
	}
}
