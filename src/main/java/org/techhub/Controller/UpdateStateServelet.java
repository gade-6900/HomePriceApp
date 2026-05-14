package org.techhub.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import org.techhub.model.StateModel;
import org.techhub.service.StateService;
import org.techhub.service.StateServiceImpl;

@WebServlet("/stateupdate")
public class UpdateStateServelet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter outPrintWriter = response.getWriter();
		
		// ✅ FIXED (removed space)
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminDashboard.html");
		requestDispatcher.include(request, response);
		
		// ✅ FIXED parameter name
		int stateId = Integer.parseInt(request.getParameter("id"));
		String stateName = request.getParameter("statename");
		
		// ✅ FIXED form action
		outPrintWriter.println("<form name='frm' action='stateupdate' method='POST'>");
		
		// ✅ FIXED class syntax
		outPrintWriter.println("<div class='container p-5 bg-dark text-white mt-1'>");
		
		outPrintWriter.println("<div class='form-group'>");
		outPrintWriter.println("<input type='text' name='id' value='"+stateId+"' class='form-control' readonly />");
		outPrintWriter.println("</div><br>");
		
		// ✅ FIXED proper state name input
		outPrintWriter.println("<div class='form-group'>");
		outPrintWriter.println("<input type='text' name='name' value='"+ (stateName != null ? stateName : "") +"' class='form-control' />");
		outPrintWriter.println("</div><br>");
		
		outPrintWriter.println("<div class='form-group'>");
		outPrintWriter.println("<input type='submit' name='s' value='Update' class='btn btn-success' />");
		outPrintWriter.println("</div>");
		
		outPrintWriter.println("</div>");
		outPrintWriter.println("</form>");
		
		// HANDLE FORM SUBMIT
		String btnString = request.getParameter("s");
		
		if(btnString != null) {
			
			// ✅ FIXED parameter names
			stateName = request.getParameter("name");
			stateId = Integer.parseInt(request.getParameter("id"));
			
			StateModel smModel = new StateModel();
			smModel.setNameString(stateName);
			smModel.setId(stateId);
			
			StateService stateService = new StateServiceImpl();
			boolean b = stateService.isUpdateState(smModel);
			
			if(b) {
				// ✅ FIXED redirect
				response.sendRedirect("viewstate");
			}else {
				outPrintWriter.println("<h1>State not updated</h1>");
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}