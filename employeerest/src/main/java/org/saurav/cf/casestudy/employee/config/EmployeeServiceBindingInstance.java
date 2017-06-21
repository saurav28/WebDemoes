package org.saurav.cf.casestudy.employee.config;

import java.util.Collections;
import java.util.Map;

import org.saurav.cf.casestudy.employee.db.EmployeeServiceInstanceBindingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingExistsException;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;

import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;

/**
 * Currently the user name will only give a single username/password
 * 
 * Future plans to give a unique username/password on each binding call.
 * @author Saurav Sarkar
 *
 */
@Service
public class EmployeeServiceBindingInstance implements ServiceInstanceBindingService{
	
	public EmployeeServiceInstanceBindingRepository bindingRepository;
	
	@Autowired
	public EmployeeServiceBindingInstance(EmployeeServiceInstanceBindingRepository bindingRepository) {
		this.bindingRepository = bindingRepository;
	}

	@Override
	public CreateServiceInstanceBindingResponse createServiceInstanceBinding(
			CreateServiceInstanceBindingRequest request) {
		
		
		String bindingId = request.getBindingId();
		String serviceInstanceId = request.getServiceInstanceId();
		
		ServiceInstanceBinding binding = bindingRepository.findOne(bindingId);
		if (binding != null) {
			throw new ServiceInstanceBindingExistsException(serviceInstanceId, bindingId);
		}

//		String database = "employee";
//		String username = "postgres";
//		String password = "admin";
		
		Map<String, Object> credentials =
				Collections.singletonMap("uri", "postgresql://postgres:admin@10.0.2.2:5432/employee");
		
		binding = new ServiceInstanceBinding(bindingId, serviceInstanceId, credentials, null, request.getBoundAppGuid());
		bindingRepository.save(binding);
		
		return new CreateServiceInstanceAppBindingResponse().withCredentials(credentials);
	}

	@Override
	public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {
		String bindingId = request.getBindingId();
		ServiceInstanceBinding binding = getServiceInstanceBinding(bindingId);

		if (binding == null) {
			throw new ServiceInstanceBindingDoesNotExistException(bindingId);
		}

		
		bindingRepository.delete(bindingId);
		
	}
	
	protected ServiceInstanceBinding getServiceInstanceBinding(String id) {
		return bindingRepository.findOne(id);
	}

}
