package com.example.midterm3.controller;

import com.example.midterm3.dto.DepartmentEmployeeDTO;
import com.example.midterm3.model.Employee;
import com.example.midterm3.service.DepartmentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/13/2026
 */

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentController {

    private DepartmentService departmentService= new DepartmentService();

    @GET
    @Path("/{id}/employees")
    public Response findByDepartmentAndSalaryRange(@PathParam("id") int id,
                                                   @QueryParam("minSalary") double minSalary,
                                                   @QueryParam("maxSalary") double maxSalary){
        DepartmentEmployeeDTO de= departmentService.findByDepartmentAndSalaryRange(id,minSalary,maxSalary);
        if (de==null){
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"khong tim thay nv\"}").build();
        }
        return Response.ok(de).build();
    }
}
