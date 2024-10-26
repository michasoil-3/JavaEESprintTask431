package servlets;

import db.DBConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value="/registration")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("name");
        try {
            if (DBConnector.checkUserExists(email)) {
                req.setAttribute("error", "The user already exists.");
                req.getRequestDispatcher("registration.jsp").forward(req, resp);
            } else {
                DBConnector.createUser(email, password, fullName);
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
