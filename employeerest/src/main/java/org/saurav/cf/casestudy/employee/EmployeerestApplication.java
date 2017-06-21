package org.saurav.cf.casestudy.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class EmployeerestApplication extends SpringBootServletInitializer{
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeerestApplication.class);
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		 	
	        return application.sources(EmployeerestApplication.class);
	    }


	public static void main(String[] args) {
		logger.info("Starting the Employee rest application");
		SpringApplication.run(EmployeerestApplication.class, args);
		
	}
}
