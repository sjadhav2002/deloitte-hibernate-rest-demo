package com.deloitte.demo.resource;

import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.service.EmployeeService;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private EmployeeService employeeService = new EmployeeService();

    @GET
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return Response.ok(employee).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); 
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Map<String, Object> payload) {
        try {
        	
        	String firstName = (String) payload.get("firstName");
            double salary = Double.parseDouble(payload.get("salary").toString());
            int deptId = Integer.parseInt(payload.get("department").toString());
            
            Employee employee = employeeService.addEmployee(firstName, salary, deptId);
            return Response.status(Response.Status.CREATED).entity(employee).build(); 
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build(); 
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") int id,Map<String, Object> payload) {
        try {
        	String firstName = (String) payload.get("firstName");
            double salary = Double.parseDouble(payload.get("salary").toString());
            int deptId = Integer.parseInt(payload.get("department").toString());
            
            Employee Emp = employeeService.updateEmployee(id, firstName, salary, deptId);
            return Response.ok(Emp).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build(); 
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        try {
            employeeService.deleteEmployee(id);
            return Response.noContent().build(); 
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build(); 
        }
    }
}