
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
            return Response.ok(Response.Status.NOT_FOUND).entity("{\"message\":\"ko thay san pham\"}").build();
        }
        return Response.ok(p).build();
    }

}

