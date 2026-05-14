package org.techhub.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.techhub.model.*;
import org.techhub.service.*;

@WebServlet("/addcity")
public class AddNewCityServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Including dashboard header/navigation
        RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.html");
        rd.include(request, response);

        StateService stateService = new StateServiceImpl();
        List<StateModel> stateList = stateService.getAllState();

        // Modern Bootstrap UI Design
        out.println("<div class='container mt-5'>");
        out.println("  <div class='row justify-content-center'>");
        out.println("    <div class='col-md-6'>");
        out.println("      <div class='card shadow border-0'>");
        out.println("        <div class='card-header bg-dark text-white p-3'>");
        out.println("          <h5 class='mb-0'>Add New City To Records</h5>");
        out.println("        </div>");
        out.println("        <div class='card-body p-4'>");
        
        out.println("          <form action='addcity' method='post'>");

        // City Input Field
        out.println("            <div class='mb-3'>");
        out.println("              <label class='form-label fw-bold text-muted small uppercase'>City Name</label>");
        out.println("              <input type='text' name='cityname' class='form-control' placeholder='Enter city name' required />");
        out.println("            </div>");

        // State Dropdown
        out.println("            <div class='mb-4'>");
        out.println("              <label class='form-label fw-bold text-muted small uppercase'>Select State</label>");
        out.println("              <select name='state' class='form-select' required>");
        out.println("                <option value=''>-- Choose State --</option>");
        for (StateModel s : stateList) {
            out.println("            <option value='" + s.getId() + "'>" + s.getNameString() + "</option>");
        }
        out.println("              </select>");
        out.println("            </div>");

        // Submit Button
        out.println("            <div class='d-grid'>");
        out.println("              <button type='submit' class='btn btn-primary btn-lg'>Add City</button>");
        out.println("            </div>");
        
        out.println("          </form>");
        out.println("        </div>"); // Close card-body
        out.println("      </div>"); // Close card
        out.println("    </div>"); // Close col
        out.println("  </div>"); // Close row
        out.println("</div>"); // Close container
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String cityName = request.getParameter("cityname");
        String stateStr = request.getParameter("state");

        // Keep inclusion of dashboard so results appear within UI
        RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.html");
        rd.include(request, response);

        out.println("<div class='container mt-4 text-center'>");

        if (cityName == null || cityName.trim().isEmpty() || stateStr == null || stateStr.isEmpty()) {
            out.println("<div class='alert alert-warning d-inline-block'>⚠️ Please fill all required fields!</div>");
        } else {
            int stateId = Integer.parseInt(stateStr);
            CityModel cityModel = new CityModel();
            cityModel.setCityName(cityName);
            cityModel.setStateId(stateId);

            CityService cityService = new CityServiceImpl();
            boolean result = cityService.isAddNewCity(cityModel);

            if (result) {
                out.println("<div class='alert alert-success d-inline-block shadow-sm'>");
                out.println("  <h4 class='alert-heading'>✅ Success!</h4>");
                out.println("  <p>City <strong>" + cityName + "</strong> has been added to the database.</p>");
                out.println("  <hr><a href='addcity' class='btn btn-outline-success btn-sm'>Add Another</a>");
                out.println("</div>");
            } else {
                out.println("<div class='alert alert-danger d-inline-block shadow-sm'>");
                out.println("  <h4 class='alert-heading'>❌ Failed</h4>");
                out.println("  <p>Could not add city. Please try again.</p>");
                out.println("</div>");
            }
        }
        out.println("</div>");
    }
}