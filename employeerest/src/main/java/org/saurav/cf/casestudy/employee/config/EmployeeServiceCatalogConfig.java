package org.saurav.cf.casestudy.employee.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Catalog end point of the employee service broker
 * @author Saurav Sarkar
 *
 */
@Configuration
public class EmployeeServiceCatalogConfig {
	
	@Bean
	public Catalog getCatalog(){
		System.out.println("get the catalog end point for the service broker");
		return new Catalog(Collections.singletonList(
				new ServiceDefinition(
						"employeerest-service-broker",
						"employeerest",
						"Employee Service broker implementation",
						true,
						false,
						Collections.singletonList(
								new Plan("employeeseevice-plan",
										"default",
										"This is a default employee seevice plan.  All services are created equally.",
										getPlanMetadata())),
						Arrays.asList("postgresql"),
						getServiceDefinitionMetadata(),
						null,
						null)));
	}
	
	private Map<String,Object> getPlanMetadata() {
		Map<String,Object> planMetadata = new HashMap<>();
		//planMetadata.put("costs", getCosts());
		//planMetadata.put("bullets", getBullets());
		return planMetadata;
	}
	
	private Map<String, Object> getServiceDefinitionMetadata() {
		Map<String, Object> sdMetadata = new HashMap<>();
		sdMetadata.put("displayName", "Employee Service");
		sdMetadata.put("imageUrl", "http://info.mongodb.com/rs/mongodb/images/MongoDB_Logo_Full.png");
		sdMetadata.put("longDescription", "Employee Rest Service");
		sdMetadata.put("providerDisplayName", "Saurav");
		sdMetadata.put("documentationUrl", "https://github.com/saurav28/WebDemoes/tree/origin/employeerest");
		sdMetadata.put("supportUrl", "https://github.com/saurav28/WebDemoes/tree/origin/employeerest");
		return sdMetadata;
	}

}
