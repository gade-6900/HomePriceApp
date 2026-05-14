package org.techhub.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.io.PrintWriter;

import org.techhub.model.StateModel;
import org.techhub.service.StateService;
import org.techhub.service.StateServiceImpl;

@WebServlet("/searchState")
public class SerachStateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter outPrintWriter = response.getWriter();
//		String snameString = request.getParameter("s");
//		outPrintWriter.println(snameString);//responce send this url
		String snameString = request.getParameter("s");
		StateService steService = new StateServiceImpl();
		
		List<StateModel> list =steService.getAllStateByList(snameString);
		
		for(StateModel model : list) {
			System.out.println(model.getId()+"\t"+ model.getNameString()+"\t");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
