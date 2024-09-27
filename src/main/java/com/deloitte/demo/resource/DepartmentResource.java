package com.deloitte.demo.resource;

import java.util.List;

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
            	return Response.status(Response.Status.BAD_REQUEST).entity("Failed").build();
            }
            return Response.status(Response.Status.CREATED).entity(newDepartment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getDepartmentById(@PathParam("id") int id) {
        try {
            Department department = departmentService.getDepartmentById(id);
            if (department != null) {
                return Response.ok(department).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateDepartment(@PathParam("id") int id, Department department) {
        try {
            Department updatedDepartment = departmentService.updateDepartment(department);
            if (updatedDepartment.getName() == null) {
            	return Response.status(Response.Status.BAD_REQUEST).entity("Department Not Found").build();
            }
            return Response.ok(updatedDepartment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartment(@PathParam("id") int id) {
        try {
            boolean res = departmentService.deleteDepartment(id);
            if (res) {
            	return Response.status(Response.Status.BAD_REQUEST).entity("Department Not Found").build();
            }
            else return Response.status(Response.Status.CREATED).entity("Success").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}