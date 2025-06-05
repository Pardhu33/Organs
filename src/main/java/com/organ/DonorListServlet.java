package com.organ;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DonorListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String search = request.getParameter("search");

        out.println("<html><head><title>Donor List</title>");
        out.println("<style>");
        out.println("table { width: 100%; border-collapse: collapse; }");
        out.println("th, td { border: 1px solid black; padding: 10px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("</style>");
        out.println("<script>");
        out.println("function showDetails(row) {");
        out.println("  alert('Full Details:\\n' +");
        out.println("        'First Name: ' + row.getAttribute('data-first-name') + '\\n' +");
        out.println("        'Last Name: ' + row.getAttribute('data-last-name') + '\\n' +");
        out.println("        'Date of Birth: ' + row.getAttribute('data-dob') + '\\n' +");
        out.println("        'Gender: ' + row.getAttribute('data-gender') + '\\n' +");
        out.println("        'Aadhar Number: ' + row.getAttribute('data-aadhar') + '\\n' +");
        out.println("        'Age: ' + row.getAttribute('data-age') + '\\n' +");
        out.println("        'Blood Group: ' + row.getAttribute('data-blood-group') + '\\n' +");
        out.println("        'Organs: ' + row.getAttribute('data-organs') + '\\n' +");
        out.println("        'Contact: ' + row.getAttribute('data-contact') + '\\n' +");
        out.println("        'Email: ' + row.getAttribute('data-email') + '\\n' +");
        out.println("        'Address: ' + row.getAttribute('data-address'));");
        out.println("}");
        out.println("</script>");
        out.println("</head><body>");
        out.println("<h2>Registered Donors</h2>");

        out.println("<form method='get' action='donorListServlet'>");
        out.println("Search: <input type='text' name='search' value='" + (search != null ? search : "") + "'>");
        out.println("<input type='submit' value='Search'>");
        out.println("</form><br>");

        out.println("<table>");
        out.println("<tr><th>First Name</th><th>Last Name</th><th>Organs</th><th>Contact</th></tr>");

        String sql = "SELECT * FROM donors";
        if (search != null && !search.trim().isEmpty()) {
            sql += " WHERE first_name LIKE ? OR last_name LIKE ? OR organs LIKE ?";
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (search != null && !search.trim().isEmpty()) {
                String keyword = "%" + search.trim() + "%";
                ps.setString(1, keyword);
                ps.setString(2, keyword);
                ps.setString(3, keyword);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.println("<tr onclick='showDetails(this)' " +
                            "data-first-name='" + rs.getString("first_name") + "' " +
                            "data-last-name='" + rs.getString("last_name") + "' " +
                            "data-dob='" + rs.getString("date_of_birth") + "' " +
                            "data-gender='" + rs.getString("gender") + "' " +
                            "data-aadhar='" + rs.getString("aadhar_number") + "' " +
                            "data-age='" + rs.getInt("age") + "' " +
                            "data-blood-group='" + rs.getString("blood_group") + "' " +
                            "data-organs='" + rs.getString("organs") + "' " +
                            "data-contact='" + rs.getString("contact") + "' " +
                            "data-email='" + rs.getString("email") + "' " +
                            "data-address='" + rs.getString("address") + "'>");
                    out.println("<td>" + rs.getString("first_name") + "</td>");
                    out.println("<td>" + rs.getString("last_name") + "</td>");
                    out.println("<td>" + rs.getString("organs") + "</td>");
                    out.println("<td>" + rs.getString("contact") + "</td>");
                    out.println("</tr>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='4'>Error retrieving donor list.</td></tr>");
        }

        out.println("</table>");
        out.println("<button onclick=\"window.history.back()\">Back</button>");
        out.println("</body></html>");
    }
}
