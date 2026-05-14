package org.techhub.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.techhub.model.StateModel;
import org.techhub.service.StateService;
import org.techhub.service.StateServiceImpl;

@WebServlet("/addstate")
public class AddStateServletform extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher r = request.getRequestDispatcher("adminDashboard.html");

		r.include(request, response);

		out.println("<form name='frm' action='addstate' method='post'>");

		out.println(
				"<div class='container d-flex justify-content-center align-items-center' style='min-height: 80vh;'>");
		out.println("<div class='glass-card p-5 shadow-lg' style='width: 100%; max-width: 450px;'>");
		out.println("<h2 class='text-center mb-4' style='color: #60a5fa; font-weight: 600;'>ADD NEW STATE</h2>");

		out.println("<div class='mb-4'>");
		out.println("<label class='form-label text-light small fw-bold'>STATE NAME</label>");
		out.println(
				"<input type='text' name='name' placeholder='e.g. Maharashtra' class='form-control bg-dark text-white border-secondary p-3' required>");
		out.println("</div>");

		out.println("<div class='d-grid'>");
		out.println("<button type='submit' name='s' class='btn btn-primary btn-lg fw-bold p-3'>SAVE STATE</button>");
		out.println("</div>");

		out.println("</div>");
		out.println("</div>");
		out.println("</form>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String btn = request.getParameter("s");
		if (btn != null) {
			StateModel sm = new StateModel();
			String stateName = request.getParameter("name");
			sm.setNameString(stateName);

			StateService stateService = new StateServiceImpl();
			boolean b = stateService.isAddState(sm);

			if (b) {
				out.println("<h1 class='text-white'>State Added Successfully ....</h1>");
			} else {
				out.println("<h1 class='text-white'>State Not Added</h1>");
			}
		}
	}
}