package com.deloitte.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.model.Employee;

public class EmployeeRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
    private EntityManager em = emf.createEntityManager();
    
    public Employee addEmployee(String firstName, double salary, int department) {
        EntityTransaction transaction = em.getTransaction();
        Department dep = findDepartmentById(department);
        if(dep == null) {
        	return new Employee();
        }
    	Employee e = new Employee(firstName, salary, dep);
    	
        try {
            transaction.begin();
            em.persist(e);
            transaction.commit();
        } catch (Exception ex) {
        	ex.getMessage();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return e;
    }

    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList(); 
    }

    public Employee getEmployeeById(int id) {
        return em.find(Employee.class, id); 
    }
    
    public List<Employee> getEmployeeByName(String name) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.firstName = :name", Employee.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public Employee updateEmployee(int id, String firstName, double salary, int department) {
        EntityTransaction transaction = em.getTransaction();
        Employee emp = new Employee();
        try {
            transaction.begin();
            Employee existingEmployee = em.find(Employee.class, id);
            if (existingEmployee != null) {
                existingEmployee.setFirstName(firstName);
                existingEmployee.setSalary(salary);
                DepartmentRepository depr = new DepartmentRepository();
                Department d = depr.getDepartmentById(department);
                if (d== null) return new Employee();
                existingEmployee.setDepartment(d);
            }
            else {
            	return new Employee();
            }
            transaction.commit();
            return existingEmployee;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return emp;
    }

    public boolean deleteEmployee(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                em.remove(employee);
                transaction.commit();
                return true;
            }
            else {
            	return false;
            }
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    public Department findDepartmentById(int departmentId) {
        EntityTransaction transaction = em.getTransaction();
        Department department = null;
        
        try {
            transaction.begin();
            department = em.find(Department.class, departmentId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return department;
    }

    
    
    public void close() {
        em.close();
        emf.close();
    }
}