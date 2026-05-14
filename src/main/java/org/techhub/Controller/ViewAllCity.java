package org.techhub.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.techhub.model.StateModel;
import org.techhub.model.CityModel;
import org.techhub.service.StateService;
import org.techhub.service.StateServiceImpl;
import org.techhub.service.CityService;
import org.techhub.service.CityServiceImpl;

@WebServlet("/viewCity")
public class ViewAllCity extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String stateIdParam = request.getParameter("stateId");

        // ✅ AJAX CALL
        if (stateIdParam != null) {
            int stateId = Integer.parseInt(stateIdParam);
            renderCityTable(response, stateId);
            return;
        }

        // ✅ NORMAL PAGE LOAD
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        RequestDispatcher r = request.getRequestDispatcher("adminDashboard.html");
        r.include(request, response);

        StateService stateService = new StateServiceImpl();
        List<StateModel> stateList = stateService.getAllState();

        out.println("<div class='container mt-5'>");

        out.println("<select id='state' onchange='fetchCities()'>");
        out.println("<option value=''>Select State</option>");

        for (StateModel s : stateList) {
            out.println("<option value='" + s.getId() + "'>" + s.getNameString() + "</option>");
        }

        out.println("</select>");

        out.println("<div id='cityResult'></div>");

        // ✅ JAVASCRIPT
        out.println("<script>");
        out.println("function fetchCities(){");
        out.println(" var sid=document.getElementById('state').value;");
        out.println(" if(sid==''){document.getElementById('cityResult').innerHTML=''; return;}");

        out.println(" var xhttp=new XMLHttpRequest();");
        out.println(" xhttp.onreadystatechange=function(){");
        out.println(" if(this.readyState==4 && this.status==200){");
        out.println(" document.getElementById('cityResult').innerHTML=this.responseText;");
        out.println(" }");
        out.println(" };");

        out.println(" xhttp.open('GET','viewCity?stateId='+sid,true);");
        out.println(" xhttp.send();");
        out.println("}");
        out.println("</script>");

        out.println("</div>");
    }

    // ✅ FIXED METHOD
    private void renderCityTable(HttpServletResponse response, int stateId) throws IOException {

        PrintWriter out = response.getWriter();

        CityService cityService = new CityServiceImpl();
        List<CityModel> cityList = cityService.getallcitybystateId(stateId);

        if (cityList != null && !cityList.isEmpty()) {

            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>City Name</th></tr>");

            for (CityModel city : cityList) {
                out.println("<tr>");
                out.println("<td>" + city.getCityId() + "</td>");
                out.println("<td>" + city.getCityName() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } else {
            out.println("<h3>No cities found</h3>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}		