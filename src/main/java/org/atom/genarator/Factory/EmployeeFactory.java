package org.atom.genarator.Factory;

import org.atom.genarator.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class EmployeeFactory {
	
	@Autowired
	private Employee employee;

	private static int id = 0;
	
	public EmployeeFactory() {
		System.out.println("Create Employee Factory Bean");
	}
	
	//@Lookup
	public Employee getEmployee() {
		//Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstName(getFirstName());
		employee.setLastName(getLastName());
		employee.setPhoneNo("9038724589");
		id++;
		return employee;
	}
	
	private String getLastName() {
		return "Lolita" + System.currentTimeMillis();
	}

	private String getFirstName() {
		return "Ram" + System.currentTimeMillis();
	}
	
	
	
	
	
	
	
}
