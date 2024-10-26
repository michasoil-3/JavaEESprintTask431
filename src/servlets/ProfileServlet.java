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

@WebServlet(value="/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = null;
        String userIdRaw = req.getParameter("userId");
        try {
            long userId = Long.parseLong(userIdRaw);
            user = DBConnector.getUserById(userId);
            req.setAttribute("user", user);
        } catch (NumberFormatException | SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (NoUserExistsException e) {
            req.getRequestDispatcher("no-such-user.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }
}
