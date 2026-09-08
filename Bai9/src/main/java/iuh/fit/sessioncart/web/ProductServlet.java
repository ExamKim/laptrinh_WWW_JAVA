package iuh.fit.sessioncart.web;

import iuh.fit.sessioncart.service.ProductCatalog;
import iuh.fit.sessioncart.session.UserSession;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/Products")
public class ProductServlet extends HttpServlet {
    @Inject
    private ProductCatalog productCatalog;
    @Inject
    private UserSession userSession;
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        if (!userSession.isLoggedIn()) {
            response.sendRedirect(request.getContextPath() +
                    "/Login");
            return;
        }
        request.setAttribute("products", productCatalog.findAll());
        request.setAttribute("userSession", userSession);
        request.getRequestDispatcher("/views/Products.jsp").forward(request, response);
    }
}