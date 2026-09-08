package iuh.fit.sessioncart.web;

import iuh.fit.sessioncart.model.Product;
import iuh.fit.sessioncart.service.ProductCatalog;
import iuh.fit.sessioncart.service.ShoppingCart;
import iuh.fit.sessioncart.session.UserSession;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/cart/add")
public class CartServlet extends HttpServlet {

    @Inject
    private ProductCatalog productCatalog;

    @Inject
    private UserSession userSession;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        if (!userSession.isLoggedIn()) {
            response.sendRedirect(
                    request.getContextPath() + "/Login"
            );
            return;
        }

        try {
            long productId =
                    Long.parseLong(request.getParameter("productId"));

            // Tìm sản phẩm theo ID
            Optional<Product> optionalProduct =
                    productCatalog.findById(productId);

            // Kiểm tra sản phẩm có tồn tại
            if (optionalProduct.isPresent()) {

                // Lấy Product
                Product product = optionalProduct.get();

                // Lấy ShoppingCart của session hiện tại
                ShoppingCart shoppingCart =
                        userSession.getShoppingCart();

                // Thêm sản phẩm vào giỏ hàng
                shoppingCart.add(product);
            }

        } catch (NumberFormatException ignored) {
            // ID không hợp lệ: không thay đổi giỏ hàng.
        }

        // PRG: tránh thêm lại sản phẩm khi refresh
        response.sendRedirect(
                request.getContextPath() + "/Products"
        );
    }
}
