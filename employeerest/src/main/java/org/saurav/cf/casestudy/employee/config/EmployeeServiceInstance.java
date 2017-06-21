package org.saurav.cf.casestudy.employee.config;

import org.saurav.cf.casestudy.employee.db.EmployeeServiceInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceExistsException;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationRequest;
import org.springframework.cloud.servicebroker.model.GetLastServiceOperationResponse;
import org.springframework.cloud.servicebroker.model.OperationState;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceResponse;

import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

/**
 * A new service instance just saves the instance info in the postgresql db
 * Code is inspired from https://github.com/spring-cloud-samples/cloudfoundry-service-broker
 * 
 * @author i054564
 *
 */
@Service
public class EmployeeServiceInstance implements ServiceInstanceService {
	
	private EmployeeServiceInstanceRepository repository;
	
	@Autowired
	public EmployeeServiceInstance(EmployeeServiceInstanceRepository repository){
		this.repository = repository;
	}

	@Override
	public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
		
		ServiceInstance instance = repository.findOne(request.getServiceInstanceId());
		if (instance != null) {
			throw new ServiceInstanceExistsException(request.getServiceInstanceId(), request.getServiceDefinitionId());
		}

		instance = new ServiceInstance(request);
		
		repository.save(instance);

		return new CreateServiceInstanceResponse();
	}

	@Override
	public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest request) {
		// TODO Auto-generated method stub
		return new GetLastServiceOperationResponse().withOperationState(OperationState.SUCCEEDED);
	}

	@Override
	public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
		String instanceId = request.getServiceInstanceId();
		ServiceInstance instance = repository.findOne(instanceId);
		if (instance == null) {
			throw new ServiceInstanceDoesNotExistException(instanceId);
		}
		repository.delete(instanceId);
		return new DeleteServiceInstanceResponse();
	}

	@Override
	public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest request) {
		String instanceId = request.getServiceInstanceId();
		ServiceInstance instance = repository.findOne(instanceId);
		if (instance == null) {
			throw new ServiceInstanceDoesNotExistException(instanceId);
		}

		repository.delete(instanceId);
		ServiceInstance updatedInstance = new ServiceInstance(request);
		repository.save(updatedInstance);
		return new UpdateServiceInstanceResponse();
	}

}
