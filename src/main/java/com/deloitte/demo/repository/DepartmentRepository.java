package com.deloitte.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.deloitte.demo.model.Department;

public class DepartmentRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
    private EntityManager em = emf.createEntityManager();
    
    public Department addDepartment(String name, String location) {
        EntityTransaction transaction = em.getTransaction();
        Department department = new Department(name);
        department.setLocation(location);
        
        try {
            transaction.begin();
            em.persist(department);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback(); 
            }
        }
        return department;
    }

    public List<Department> getAllDepartments() {
        TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d", Department.class);
        return query.getResultList();
    }

    public Department getDepartmentById(int id) {
        return em.find(Department.class, id); 
    }

    public void updateDepartment(Department updatedDepartment) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Department existingDepartment = em.find(Department.class, updatedDepartment.getId());
            if (existingDepartment != null) {
                existingDepartment.setName(updatedDepartment.getName());
                existingDepartment.setLocation(updatedDepartment.getLocation());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); 
            }
            e.printStackTrace();
        }
    }

    public void deleteDepartment(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Department department = em.find(Department.class, id);
            if (department != null) {
                em.remove(department); 
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); 
            }
            e.printStackTrace();
        }
    }

    public void close() {
        em.close();
        emf.close();
    }
}