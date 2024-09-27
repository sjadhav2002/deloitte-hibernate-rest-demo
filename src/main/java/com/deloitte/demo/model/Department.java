package com.deloitte.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String location;

	public Department() {
		super();
	}

	public Department(String Name) {
		super();
		this.name = Name;
	}


	public int getId() {
		return id;
	}

	public String getName() {
		if (this.name == null) return null;
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", Name=" + name +", Location=" + location +"]";
	}

}


//CREATE TABLE department (
//	    id INT AUTO_INCREMENT PRIMARY KEY,
//	    name VARCHAR(255) UNIQUE NOT NULL ,
//		location VARCHAR(255) NOT NULL
//	);
//    
//Insert into department values (1,'Technical', 'Banglore');
//Insert into department values (2,'Sales', 'Hyderabad');
//Insert into department values (3,'Human Resources', 'Pune');