
package fit.vn.midterm1.controller;

import fit.vn.midterm1.entity.Product;
import fit.vn.midterm1.repository.ProductRepository;
import fit.vn.midterm1.service.ProductService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {
    private ProductService productService= new ProductService();


    @GET
    public Response getAll(){
        List<Product> p= productService.getAllProducts();
        return Response.ok(p).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id){
        Product p= productService.getProductById(id);
        if (p==null){
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"ko thay san pham\"}").build();
        }
        return Response.ok(p).build();
    }

    @POST
    public Response create(Product p){
        boolean success= productService.createProduct(p);
        if (success){
            return Response.status(Response.Status.CREATED).entity("{\"message\":\"Tao sp thanh cong\"}").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\":\"Tao sp that bai\"}").build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Product p){
        boolean success= productService.updateProduct(p);
        if (success){
            return Response.ok("{\"message\":\" Cap nhat thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).entity("{\"message\":\"Cap nhat that bai\"}").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        boolean success= productService.deleteProduct(id);
        if (success){
            return Response.ok("{\"message\":\" Xoa thanh cong\"}").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"Khong tim thay san pham can xoa\"}").build();
    }

}

