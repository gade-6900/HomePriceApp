package org.techhub.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.techhub.model.LocationModel;
import org.techhub.service.LocationService;
import org.techhub.service.Locationserviceimpl;

@WebServlet("/saveloc")
public class SaveLocationServelt extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String locname = request.getParameter("name");
		int ctid = Integer.parseInt(request.getParameter("city"));
		
		LocationModel locationModel = new LocationModel();
		
		locationModel.setLocnameString(locname);
		locationModel.setCtid(ctid);
		
		LocationService locationService = new Locationserviceimpl();
		boolean saved = locationService.saveLocation(locationModel);
		
		
		if(saved) {
			response.sendRedirect("addnewloc?msg=success");
			
		}else {
			response.sendRedirect("addnewloc?msg=error");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
