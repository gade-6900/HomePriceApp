package org.techhub.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import org.techhub.model.CityModel;
import org.techhub.model.StateModel;
import org.techhub.service.CityService;
import org.techhub.service.CityServiceImpl;
import org.techhub.service.StateService;
import org.techhub.service.StateServiceImpl;

@WebServlet("/addnewloc")
public class AddNewLocation extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminDashboard.html");
        requestDispatcher.include(request, response);

        // Load states
        StateService stateService = new StateServiceImpl();
        List<StateModel> stateList = stateService.getAllState();

        // Load ALL cities for ALL states as JSON
        CityService cityService = new CityServiceImpl();
        StringBuilder cityJson = new StringBuilder("{");
        for (int i = 0; i < stateList.size(); i++) {
            StateModel state = stateList.get(i);
            List<CityModel> cities = cityService.getallcitybystateId(state.getId());
            cityJson.append("\"").append(state.getId()).append("\":[");
            for (int j = 0; j < cities.size(); j++) {
                CityModel city = cities.get(j);
                cityJson.append("{\"id\":").append(city.getCityId())
                        .append(",\"name\":\"").append(city.getCityName()).append("\"}");
                if (j < cities.size() - 1) cityJson.append(",");
            }
            cityJson.append("]");
            if (i < stateList.size() - 1) cityJson.append(",");
        }
        cityJson.append("}");

        // ── UI ──────────────────────────────────────────────────────────────
        out.println("<div class='container mt-5'>");
        out.println("  <div class='row justify-content-center'>");
        out.println("    <div class='col-md-6'>");
        out.println("      <div class='card shadow-lg border-0'>");
        out.println("        <div class='card-header bg-primary text-white text-center'>");
        out.println("          <h4 class='mb-0'>Add New Location</h4>");
        out.println("        </div>");
        out.println("        <div class='card-body p-4'>");

        // ✅ Success / Error Message INSIDE card, ABOVE form
        String msg = request.getParameter("msg");
        if (msg != null) {
            if (msg.equals("success")) {
                out.println("<div class='alert alert-success text-center fw-bold'>");
                out.println("  <i class='bi bi-check-circle-fill'></i> Location saved successfully!");
                out.println("</div>");
            } else {
                out.println("<div class='alert alert-danger text-center fw-bold'>");
                out.println("  <i class='bi bi-x-circle-fill'></i> Failed to save location. Please try again.");
                out.println("</div>");
            }
        }

        // ✅ Fixed: action='saveloc' matches @WebServlet("/saveloc")
        out.println("          <form action='saveloc' method='POST'>");

        // Location Name
        out.println("            <div class='mb-3'>");
        out.println("              <label class='form-label fw-bold'>Location Name</label>");
        out.println("              <input type='text' name='name' placeholder='Enter location name' class='form-control' required />");
        out.println("            </div>");

        // State dropdown
        out.println("            <div class='mb-3'>");
        out.println("              <label class='form-label fw-bold'>Select State</label>");
        out.println("              <select name='state' id='state' class='form-select' onchange='loadCities()' required>");
        out.println("                <option value='' disabled selected>-- Select State --</option>");
        for (StateModel model : stateList) {
            out.println("                <option value='" + model.getId() + "'>" + model.getNameString() + "</option>");
        }
        out.println("              </select>");
        out.println("            </div>");

        // Search Bar - hidden until state is selected
        out.println("            <div class='mb-3' id='searchDiv' style='display:none;'>");
        out.println("              <label class='form-label fw-bold'>Search City</label>");
        out.println("              <input type='text' id='searchBar' class='form-control' placeholder='Type to search city...' onkeyup='filterCities()' />");
        out.println("            </div>");

        // City dropdown - hidden until state is selected
        out.println("            <div class='mb-3' id='cityDiv' style='display:none;'>");
        out.println("              <label class='form-label fw-bold'>Select City</label>");
        out.println("              <select name='city' id='city' class='form-select' required>");
        out.println("                <option value=''>-- Select City --</option>");
        out.println("              </select>");
        out.println("            </div>");

        // Submit Button
        out.println("            <div class='d-grid gap-2 mt-4'>");
        out.println("              <button type='submit' class='btn btn-success btn-lg'>Save Location</button>");
        out.println("            </div>");

        out.println("          </form>");

        // ── JavaScript ──────────────────────────────────────────────────────
        out.println("          <script>");
        out.println("            var cityData = " + cityJson.toString() + ";");
        out.println("            var allCities = [];");

        // loadCities() — triggered when state changes
        out.println("            function loadCities() {");
        out.println("              var stateId = document.getElementById('state').value;");
        out.println("              var citySelect = document.getElementById('city');");
        out.println("              document.getElementById('searchBar').value = '';");
        out.println("              citySelect.innerHTML = '<option value=\"\">-- Select City --</option>';");
        out.println("              allCities = cityData[stateId] || [];");
        out.println("              allCities.forEach(function(c) {");
        out.println("                var opt = document.createElement('option');");
        out.println("                opt.value = c.id;");
        out.println("                opt.textContent = c.name;");
        out.println("                citySelect.appendChild(opt);");
        out.println("              });");
        out.println("              document.getElementById('searchDiv').style.display = 'block';");
        out.println("              document.getElementById('cityDiv').style.display = 'block';");
        out.println("            }");

        // filterCities() — triggered on every keyup in search bar
        out.println("            function filterCities() {");
        out.println("              var query = document.getElementById('searchBar').value.toLowerCase();");
        out.println("              var citySelect = document.getElementById('city');");
        out.println("              citySelect.innerHTML = '<option value=\"\">-- Select City --</option>';");
        out.println("              var filtered = allCities.filter(function(c) {");
        out.println("                return c.name.toLowerCase().indexOf(query) !== -1;");
        out.println("              });");
        out.println("              filtered.forEach(function(c) {");
        out.println("                var opt = document.createElement('option');");
        out.println("                opt.value = c.id;");
        out.println("                opt.textContent = c.name;");
        out.println("                citySelect.appendChild(opt);");
        out.println("              });");
        out.println("            }");

        // Auto-hide alert after 3 seconds
        out.println("            window.onload = function() {");
        out.println("              var alertBox = document.querySelector('.alert');");
        out.println("              if (alertBox) {");
        out.println("                setTimeout(function() {");
        out.println("                  alertBox.style.transition = 'opacity 1s';");
        out.println("                  alertBox.style.opacity = '0';");
        out.println("                  setTimeout(function() { alertBox.style.display = 'none'; }, 1000);");
        out.println("                }, 3000);");
        out.println("              }");
        out.println("            };");

        out.println("          </script>");

        out.println("        </div>"); // End Card Body
        out.println("      </div>"); // End Card
        out.println("    </div>"); // End Col
        out.println("  </div>"); // End Row
        out.println("</div>"); // End Container
    }
}