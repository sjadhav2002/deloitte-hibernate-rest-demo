package com.deloitte.demo.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.service.DepartmentService;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    private DepartmentService departmentService = new DepartmentService();

    @GET
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @POST
    public Response addDepartment(Department department) {
        try {
            Department newDepartment = departmentService.addDepartment(department.getName(), department.getLocation());
            if (newDepartment.getName() == null) {
            	Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("msg", "Failed");
            	
            	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
            }
            return Response.status(Response.Status.CREATED).entity(newDepartment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.ACCEPTED).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getDepartmentById(@PathParam("id") int id) {
        try {
            Department department = departmentService.getDepartmentById(id);
            if (department.getName() != null) {
                return Response.ok(department).build();
            } else {
            	Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("msg", "Failed");
                errorResponse.put("reason", "Department not found");
            	
            	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
            }
        } catch (IllegalArgumentException e) {
        	Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("msg", "Failed");
            errorResponse.put("reason", "Internal Server Error");
        	
        	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateDepartment(@PathParam("id") int id, Department department) {
        try {
            Department updatedDepartment = departmentService.updateDepartment(department);
            if (updatedDepartment.getName() == null) {
            	Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("msg", "Failed");
                errorResponse.put("reason", "Department not found");
            	
            	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
            }
            return Response.ok(updatedDepartment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.ACCEPTED).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartment(@PathParam("id") int id) {
        try {
            boolean res = departmentService.deleteDepartment(id);
            if (!res) {
            	Map<String, String> errorResponse = new HashMap<>();
            	errorResponse.put("msg", "Failed");
            	errorResponse.put("reason", "Department not found");
        	
        	return Response.status(Response.Status.ACCEPTED).entity(errorResponse).build();
            }
            else {
            Map<String, String> Resp = new HashMap<>();
            Resp.put("msg", "Success");
        	
        	return Response.status(Response.Status.ACCEPTED).entity(Resp).build();}
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.ACCEPTED).entity(e.getMessage()).build();
        }
    }
}