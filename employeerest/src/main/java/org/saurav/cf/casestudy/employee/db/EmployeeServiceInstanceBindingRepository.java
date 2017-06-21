package org.saurav.cf.casestudy.employee.db;


import org.saurav.cf.casestudy.employee.config.ServiceInstanceBinding;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeServiceInstanceBindingRepository extends PagingAndSortingRepository<ServiceInstanceBinding, String> {
}
