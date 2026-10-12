package iuh.edu.fit.midterm2.controller;

import iuh.edu.fit.midterm2.dto.DanhMucTinTucDTO;
import iuh.edu.fit.midterm2.model.DanhMuc;
import iuh.edu.fit.midterm2.service.DanhMucService;
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

@Path("/danhmuc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DanhMucController {
    private DanhMucService danhMucService= new DanhMucService();

    @GET
    public Response getAll(){
        List<DanhMuc> list= danhMucService.getAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{madm}")
    public Response getById(@PathParam("madm") int id){
        DanhMuc dm= danhMucService.getById(id);
        if(dm==null){
            return Response.status(Response.Status.NOT_FOUND).entity("{\"messege\":\"Khong tim thay\"}").build();
        }
        return Response.ok(dm).build();
    }

    @PUT
    @Path("/{madm}")
    public Response update(@PathParam("madm") int id, DanhMuc dm){
        boolean success= danhMucService.update(dm);
        if(success){
            return Response.ok("{\"messege\":\"Cap nhat thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"messege\":\"Khong tim thay\"}").build();
    }

    @POST
    public Response save( DanhMuc dm){
        boolean success= danhMucService.save(dm);
        if(success){
            return Response.ok("{\"messege\":\"Them thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"messege\":\"Khong tim thay\"}").build();
    }

    @DELETE
    @Path("/{madm}")
    public Response delete(@PathParam("madm") int id){
        boolean success= danhMucService.delete(id);
        if(success){
            return Response.ok("{\"messege\":\"Xoa thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"messege\":\"Khong tim thay\"}").build();
    }

    @GET
    @Path("/soluongtintuc")
    public Response getSoLuongTinTuc(){
        List<DanhMucTinTucDTO> list= danhMucService.getSoLuongTinTuc();
        return Response.ok(list).build();
    }
    @GET
    @Path("/soluongtu3tintuc")
    public Response getListTu3TinTuc(){
        List<DanhMucTinTucDTO> list= danhMucService.getListTu3TinTuc();
        return Response.ok(list).build();
    }

    @GET
    @Path("/max")
    public Response getMax(){
        DanhMucTinTucDTO dm= danhMucService.getMax();
        if(dm==null){
            return Response.status(Response.Status.NOT_FOUND).entity("{\"messege\":\"Khong tim thay\"}").build();
        }
        return Response.ok(dm).build();
    }
    @GET
    @Path("/thongketyle")
    public Response getThongKeTyLe() {
        List<DanhMucTinTucDTO> list = danhMucService.getThongKeTyLeTinTuc();
        return Response.ok(list).build();
    }
}
