package com.deloitte.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private double salary;
	
	@ManyToOne
    @JoinColumn(name = "deptId", referencedColumnName = "id")
    private Department department;

	public Employee() {
		super();
	}

	public Employee(String firstName, double salary) {
		super();
		this.name = firstName;
		this.salary = salary;
	}

	public Employee(String firstName, double salary, Department department ) {
		super();
		this.department = department;
		this.name = firstName;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return name;
	}

	public void setFirstName(String firstName) {
		this.name = firstName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getDepartment() {
		if (this.department == null)return null;
		return department.getName();
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + name + ", salary=" + salary + ", department=" + department.getName() + "]";
	}

}



//CREATE TABLE employee (
//	    id INT AUTO_INCREMENT PRIMARY KEY,
//	    name VARCHAR(255) NOT NULL,
//	    salary DOUBLE NOT NULL,
//	    deptId INT,
//	    FOREIGN KEY (deptId) REFERENCES department(id)
//	);
//
//
//	Insert into employee values (1, 'Sonu', 95.2, 1);
//	Insert into employee values (2, 'Monu', 92, 2);
//	Insert into employee values (3, 'Ponu', 93, 3);
//	Insert into employee values (4, 'Tonu', 94, 1);