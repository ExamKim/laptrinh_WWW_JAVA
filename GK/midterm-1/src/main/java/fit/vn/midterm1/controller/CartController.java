package fit.vn.midterm1.controller;

import fit.vn.midterm1.dto.CartDetailDTO;
import fit.vn.midterm1.entity.Cart;
import fit.vn.midterm1.entity.Product;
import fit.vn.midterm1.service.CartService;
import fit.vn.midterm1.service.ProductService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/10/2026
 */

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartController {
    private CartService cartService= new CartService();


    @GET
    public Response getAll(){
        List<Cart> c= cartService.getAllCarts();
        return Response.ok(c).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id){
        Cart c= cartService.getCartById(id);
        if (c==null){
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"ko thay gio hang\"}").build();
        }
        return Response.ok(c).build();
    }

    @POST
    public Response create(Cart c){
        boolean success= cartService.createCart(c);
        if (success){
            return Response.status(Response.Status.CREATED).entity("{\"message\":\"Tao gio hang thanh cong\"}").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\":\"Tao gio hang that bai\"}").build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Cart c){
        boolean success= cartService.updateCart(c);
        if (success){
            return Response.ok("{\"message\":\" Cap nhat thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).entity("{\"message\":\"Cap nhat that bai\"}").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        boolean success= cartService.deleteCart(id);
        if (success){
            return Response.ok("{\"message\":\" Xoa thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"Khong tim thay gio hang can xoa\"}").build();
    }

    @GET
    @Path("/details")
    public Response getCartDetail(){
        List<CartDetailDTO> c= cartService.getCartDetail();
        return Response.ok(c).build();
    }
}
