package org.techhub.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Optional;

import org.techhub.model.AdminLoginModel;
import org.techhub.service.ValidateAdminService;
import org.techhub.service.ValidateAdminServiceImpl;

@WebServlet("/validateAdmin")
public class AdminLoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AdminLoginModel model = new AdminLoginModel();
        model.setUsername(username);
        model.setPassword(password);

        ValidateAdminService service = new ValidateAdminServiceImpl();
        Optional<AdminLoginModel> o = service.validateAdmin(model);

        if (o.isPresent()) {
            RequestDispatcher r = request.getRequestDispatcher("adminDashboard.html");
            r.forward(request, response);
        } else {
        	 RequestDispatcher r = request.getRequestDispatcher("login.html");
             r.include(request, response);
             out.println("<h1>Invalid user</h1>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}