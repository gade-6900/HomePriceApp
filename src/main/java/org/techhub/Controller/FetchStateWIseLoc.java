package org.techhub.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.techhub.model.CityModel;
import org.techhub.service.CityService;
import org.techhub.service.CityServiceImpl;


@WebServlet("/fetchstatewiseloc")
public class FetchStateWIseLoc extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        int stateId = Integer.parseInt(request.getParameter("stid"));
        CityService cityService = new CityServiceImpl();
        List<CityModel> cityList = cityService.getallcitybystateId(stateId);
        String str = "";
       
        int count = 0;
        for(CityModel c : cityList) {
            count++;
            
        }
        
       
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
