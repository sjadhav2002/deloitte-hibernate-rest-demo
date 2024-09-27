package com.deloitte.demo.resource;

import java.util.HashMap;
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
        	Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("msg", "Failed");
            errorResponse.put("reason", "Employee not found");
        	
        	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
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
            if(employee.getDepartment() == null) {
            	Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("msg", "Failed");
                errorResponse.put("reason", "Department not found");
            	
            	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build(); 
            }
            return Response.status(Response.Status.CREATED).entity(employee).build(); 
        } catch (IllegalArgumentException e) {
        	Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("msg", "Failed");
            errorResponse.put("reason", "Internal Server Error");
        	
        	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
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
            if (Emp.getDepartment()==null) {
            	Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("msg", "Failed");
                errorResponse.put("reason", "Employee or Department not found");
            	
            	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
            }; 
            Map<String, String> resp = new HashMap<>();
            resp.put("msg", "Success");
        	return Response.status(Response.Status.ACCEPTED).entity(resp).build();
        } catch (IllegalArgumentException e) {
        	Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("msg", "Failed");
            errorResponse.put("reason", "Employee not found");
        	
        	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build(); 
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        try {
            boolean b = employeeService.deleteEmployee(id);
            if (b) {
            	Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("msg", "Success");
            	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
            } 
            else {
            	Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("msg", "Failed");
                errorResponse.put("reason", "Employee not found");
            	
            	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.ACCEPTED).entity(e.getMessage()).build(); 
        }
    }
}