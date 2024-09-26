package com.deloitte.demo.resource;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.service.EmployeeService;

@Path("/employees")
public class EmployeeResource {

	private EmployeeService empserv = new EmployeeService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmployees() {
		return empserv.getAllEmployees();
	}
	
	
	@GET
	@Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public String getEmployeeById(@PathParam("id") int id) {
		System.out.println(id);
		System.out.println("Called");
		List<Employee> empList = empserv.getAllEmployees();
		for(Employee e : empList) {
			if(e.getId() == id) {
				return e.toString();
			}
		}
		return "Employee Not Found";
	}
	
	@POST
	@Path("/addEmployee")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addEmployee(Employee employee) {
		if(employee.getId() < 0 && employee.getFirstName() != null && employee.getSalary() != 0 && employee.getFirstName() != "") {
			return "Failed";
		}
		boolean res = empserv.addEmployee(employee);
		if (res) return "Success";
		else return "Employee Not Found";
	}
	
	@POST
	@Path("/updateEmployee")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateEmployee(Employee employee) {
		
		if(employee.getId() < 0 || employee.getFirstName() != null || employee.getSalary() != 0 || employee.getFirstName() != "") {
			return "Failed";
		}
		List<Employee> empList = empserv.getAllEmployees();
		for(Employee e : empList) {
			if(e.getId() == employee.getId()) {
				e.setFirstName(employee.getFirstName());
				e.setSalary(employee.getSalary());
				return "Success";
			}
		}
		return "Employee not found";
	}
	
	@DELETE
	@Path("/deleteEmployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public String deleteEmployee(@PathParam("id") int id) {
		boolean res = empserv.delEmployee(id);
		if (res) return "Success";
		else return "Employee Not Found";
	}
	

}