
package org.techhub.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.techhub.model.CityModel;
import org.techhub.service.CityService;
import org.techhub.service.CityServiceImpl;

@WebServlet("/fetchcity")
public class FetchStateWisecity extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        int stateId = Integer.parseInt(request.getParameter("stid"));
        CityService cityService = new CityServiceImpl();
        List<CityModel> cityList = cityService.getallcitybystateId(stateId);
        
        // UI Start: Table design with Bootstrap classes
        String str = "<table class='table table-bordered table-hover text-center'>";
        str += "<thead class='table-dark'>";
        str += "<tr><th>SRNO</th><th>CITY NAME</th><th>DELETE</th><th>UPDATE</th></tr>";
        str += "</thead>";
        str += "<tbody>";
        
        int count = 0;
        for(CityModel c : cityList) {
            count++;
            str += "<tr>";
            str += "<td>" + count + "</td>";
            str += "<td>" + c.getCityName() + "</td>";
            
            // Styled buttons instead of broken links
            str += "<td><a href='delete?cid="+c.getCityId()+"' class='btn btn-danger btn-sm'>Delete</a></td>";
            str += "<td><a href='update?cid="+c.getCityId()+"' class='btn btn-primary btn-sm'>Update</a></td>";
            str += "</tr>";
        }
        
        str += "</tbody></table>";
        
        // Final Output
        out.print(str);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}  
    

   