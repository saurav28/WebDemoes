package org.saurav.cf.casestudy.employee.ui;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class EmployeeController {

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployee(){
		return "employee";
	}
}
