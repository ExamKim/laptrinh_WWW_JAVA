package iuh.edu.fit.midterm2.controller;

import iuh.edu.fit.midterm2.dto.DanhMucTinTucDTO;
import iuh.edu.fit.midterm2.model.DanhMuc;
import iuh.edu.fit.midterm2.model.TinTuc;
import iuh.edu.fit.midterm2.service.DanhMucService;
import iuh.edu.fit.midterm2.service.TinTucService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
@Path("/tintuc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TinTucController {
    private TinTucService tinTucService= new TinTucService();

    @GET
    public Response getAll(){
        List<TinTuc> list= tinTucService.getAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{matt}")
    public Response getById(@PathParam("matt") int id){
        TinTuc tt= tinTucService.getById(id);
        if(tt==null){
            return Response.status(Response.Status.NOT_FOUND).entity("{\"messege\":\"Khong tim thay\"}").build();
        }
        return Response.ok(tt).build();
    }

    @PUT
    @Path("/{matt}")
    public Response update(@PathParam("matt") int id, TinTuc tt){
        boolean success= tinTucService.update(tt);
        if(success){
            return Response.ok("{\"messege\":\"Cap nhat thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"messege\":\"Khong tim thay\"}").build();
    }

    @POST
    public Response save( TinTuc dm){
        boolean success= tinTucService.save(dm);
        if(success){
            return Response.ok("{\"messege\":\"Them thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"messege\":\"Khong tim thay\"}").build();
    }

    @DELETE
    @Path("/{matt}")
    public Response delete(@PathParam("matt") int id){
        boolean success= tinTucService.delete(id);
        if(success){
            return Response.ok("{\"messege\":\"Xoa thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"messege\":\"Khong tim thay\"}").build();
    }

    @GET
    @Path("/details")
    public Response getListKemDanhMuc(){
        List<DanhMucTinTucDTO> list= tinTucService.getListKemDanhMuc();
        return Response.ok(list).build();
    }
    @GET
    @Path("/danhmuc")
    public Response getListKemDanhMuc(@QueryParam("danhmuc") String tendm){
        List<DanhMucTinTucDTO> list= tinTucService.getListThuocDanhMuc(tendm);
        return Response.ok(list).build();
    }
}
