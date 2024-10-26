package servlets;

import db.DBConnector;
import exceptions.NoUserExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value="/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = null;
        ArrayList<Product> products = null;

        // Getting user
        long userId;
        String userIdRaw = req.getParameter("userId");
        try {
            userId = Long.parseLong(userIdRaw);
            user = DBConnector.getUserById(userId);
        }
        catch (NoUserExistsException e) {
            req.getRequestDispatcher("no-such-user.jsp").forward(req, resp);
            return;
        }

        catch (NumberFormatException | SQLException _) {
        }

        // Getting products
        try {
            products = DBConnector.getProducts();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("products", products);
        req.setAttribute("user", user);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
