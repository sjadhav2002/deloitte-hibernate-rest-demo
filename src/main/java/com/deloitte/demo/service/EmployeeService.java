package com.deloitte.demo.service;

import java.util.List;

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.repository.EmployeeRepository;

public class EmployeeService {

    private EmployeeRepository employeeRepository = new EmployeeRepository();

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee addEmployee(String firstName, double salary, int department) {
        if (department <= 0 || firstName == null || salary <= 0) {
            throw new IllegalArgumentException("Invalid employee details provided.");
        }
        Employee e = employeeRepository.addEmployee(firstName, salary, department);
        return e; 
    }

    public Employee updateEmployee(Employee updatedEmployee) {
        if (updatedEmployee.getId() <= 0) {
            throw new IllegalArgumentException("Invalid employee ID.");
        }
        System.out.println(updatedEmployee.toString());
        employeeRepository.updateEmployee(updatedEmployee);
        return updatedEmployee; 
    }

    public void deleteEmployee(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid employee ID.");
        }
        employeeRepository.deleteEmployee(id);
    }

    public Employee getEmployeeById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid employee ID.");
        }
        return employeeRepository.getEmployeeById(id);
    }
}