package servlets;

import db.DBConnector;
import exceptions.NoUserExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value="/login")
public class SignInServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User();
        try {
            user = DBConnector.getAsUser(email, password);
        } catch (SQLException | NoUserExistsException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/?userId=" + user.getId());
    }
}
