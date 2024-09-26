package com.deloitte.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.demo.model.Employee;

public class EmployeeService {
	

	private List<Employee> empList = new ArrayList<>(
	        List.of(new Employee(1, "Sonu", 90.25), 
	                new Employee(2, "Monu", 95.75),
	                new Employee(3, "Tonu", 92.25))
	    );

	public List<Employee> getAllEmployees() {
		empList.forEach(System.out::println);
		return empList;
	}
	
	public boolean addEmployee(Employee e) {
		return empList.add(e);
	}
	
	public boolean delEmployee(int id) {
		return empList.removeIf(emp -> emp.getId() == id);
	}

}