package org.techhub.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.techhub.model.StateModel;
import org.techhub.service.StateService;
import org.techhub.service.StateServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewstate")
public class ViewAllState extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String path = request.getContextPath();

        StateService stateService = new StateServiceImpl();
        List<StateModel> list = stateService.getAllState();

        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>");
        out.println("<title>State Management</title>");

        // ✅ JS for search
        out.println("<script>");
        out.println("function searchState() {");
        out.println("  let input = document.getElementById('searchInput').value.toLowerCase();");
        out.println("  let table = document.getElementById('stateTable');");
        out.println("  let tr = table.getElementsByTagName('tr');");
        out.println("  for (let i = 1; i < tr.length; i++) {");
        out.println("    let td = tr[i].getElementsByTagName('td')[1];");
        out.println("    if (td) {");
        out.println("      let text = td.innerText.toLowerCase();");
        out.println("      tr[i].style.display = text.includes(input) ? '' : 'none';");
        out.println("    }");
        out.println("  }");
        out.println("}");
        out.println("</script>");

        out.println("</head><body class='bg-light'>");

        RequestDispatcher r = request.getRequestDispatcher("adminDashboard.html");
        r.include(request, response);

        out.println("<div class='container mt-5'>");
        out.println("  <div class='card shadow-sm'>");

        out.println("    <div class='card-header bg-dark text-white d-flex justify-content-between align-items-center'>");
        out.println("      <h5 class='mb-0'>Registered States</h5>");
        out.println("      <a href='" + path + "/addstate' class='btn btn-outline-light btn-sm'>Add New State</a>");
        out.println("    </div>");

        // ✅ Search Box (added)
        out.println("<div class='p-3'>");
        out.println("<div class='input-group'>");
        out.println("<input type='text' id='searchInput' class='form-control' placeholder='Search state...'>");
        out.println("<button class='btn btn-primary' onkeyup='searchState(this.value)'>Search</button>");
        out.println("</div>");
        out.println("</div>");

        out.println("    <div class='card-body p-0'>");

        // ✅ Added table ID
        out.println("      <table id='stateTable' class='table table-hover mb-0'>");
        out.println("        <thead class='table-secondary'>");
        out.println("          <tr><th>SRNO</th><th>STATE NAME</th><th class='text-center'>ACTIONS</th></tr>");
        out.println("        </thead>");
        out.println("        <tbody>");

        if (list != null && !list.isEmpty()) {
            int count = 0;
            for (StateModel sm : list) {
                count++;
                out.println("<tr>");
                out.println("  <td>" + count + "</td>");
                out.println("  <td class='fw-bold'>" + sm.getNameString() + "</td>");
                out.println("  <td class='text-center'>");
                
                out.println("<a href='" + path + "/stateupdate?id=" + sm.getId()  + "' class='btn btn-sm btn-info me-2'><i class='fas fa-edit'></i> Edit</a>");
                
                out.println("<a href='" + path + "/deletestate?stateid=" + sm.getId() + "' class='btn btn-sm btn-danger' onclick='return confirm(\"Are you sure you want to delete this state?\")'><i class='fas fa-trash'></i> Delete</a>");
                
                out.println("  </td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td colspan='3' class='text-center text-muted p-4'>No states found in the database.</td></tr>");
        }

        out.println("        </tbody>");
        out.println("      </table>");
        out.println("    </div>"); 
        out.println("  </div>"); 
        out.println("</div>"); 

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}