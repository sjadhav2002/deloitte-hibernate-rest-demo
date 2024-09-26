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
	
	private String firstName;
	private double salary;
	
	@ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "deptId") // Foreign key in Employee table referencing Department's primary key
    private Department department;

	public Employee() {
		super();
	}

	public Employee(String firstName, double salary) {
		super();
		this.firstName = firstName;
		this.salary = salary;
	}

	public Employee(String firstName, double salary, Department department ) {
		super();
		this.department = department;
		this.firstName = firstName;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getDepartment() {
		return department.getName();
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", salary=" + salary + ", department=" + department + "]";
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