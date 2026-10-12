package com.example.midterm3.controller;

import com.example.midterm3.model.Employee;
import com.example.midterm3.service.EmployeeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {
    private EmployeeService employeeService=new EmployeeService();

    @GET
    public Response findAll(){
        List<Employee> list= employeeService.findAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id){
        Employee em= employeeService.findById(id);
        if (em==null){
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"khong tim thay nv\"}").build();
        }
        return Response.ok(em).build();
    }

    @POST
    public Response save(Employee em){
        boolean success= employeeService.save(em);
        if (success){
            return Response.ok("{\"message\":\"Luu Thanh Cong\"}").build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).entity("{\"message\":\"khong luu duoc\"}").build();

    }

}
