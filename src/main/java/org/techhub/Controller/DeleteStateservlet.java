package org.techhub.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.techhub.service.StateService;
import org.techhub.service.StateServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deletestate")
public class DeleteStateservlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("stateid");

        if (id != null) {
            int stateId = Integer.parseInt(id);

            StateService stateService = new StateServiceImpl();
            boolean b = stateService.isDeleteStateById(stateId);

            if (b) {
                // ✅ FIX: use context path
                response.sendRedirect(request.getContextPath() + "/viewstate");
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                RequestDispatcher r = request.getRequestDispatcher("adminDashboard.html");
                r.include(request, response);
                out.println("<h1>State not deleted</h1>");
            }
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h1>Invalid State ID</h1>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}