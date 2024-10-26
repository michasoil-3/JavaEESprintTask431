package servlets;

import db.DBConnector;
import exceptions.NoUserExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value="/delete")
public class DeleteAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userIdRaw = req.getParameter("userId");

        try {
            long userId = Long.parseLong(userIdRaw);
            DBConnector.deleteUser(userId);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
