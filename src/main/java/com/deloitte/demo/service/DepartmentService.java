package com.deloitte.demo.service;

import java.util.List;
import com.deloitte.demo.model.Department;
import com.deloitte.demo.repository.DepartmentRepository;

public class DepartmentService {

    private DepartmentRepository departmentRepository = new DepartmentRepository();

       public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

        public Department addDepartment(String name, String location) {
        if (name == null || name.isEmpty() || location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Invalid department details provided.");
        }
        
        Department d = departmentRepository.addDepartment(name, location);
        return d;
    }

    
    public Department updateDepartment(Department updatedDepartment) {
        if (updatedDepartment.getId() <= 0) {
            throw new IllegalArgumentException("Invalid department ID.");
        }
        departmentRepository.updateDepartment(updatedDepartment);
        return updatedDepartment;
    }

        public void deleteDepartment(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid department ID.");
        }
        departmentRepository.deleteDepartment(id);
    }

        public Department getDepartmentById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid department ID.");
        }
        return departmentRepository.getDepartmentById(id);
    }
}