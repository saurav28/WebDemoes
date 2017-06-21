package org.saurav.cf.casestudy.employee.db;

import org.saurav.cf.casestudy.employee.config.ServiceInstance;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * This is the repository to save service instance related info in the Postgresql database
 * @author Saurav Sarkar
 *
 */
public interface EmployeeServiceInstanceRepository extends PagingAndSortingRepository<ServiceInstance, String> {

}
