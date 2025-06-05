package com.organ;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DonorRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String dob = request.getParameter("date_of_birth");
        String gender = request.getParameter("gender");
        String aadhar = request.getParameter("aadhar_number");

        String ageParam = request.getParameter("age");
        int age;
        try {
            if (ageParam != null && !ageParam.trim().isEmpty()) {
                age = Integer.parseInt(ageParam.trim());
                if (age <= 0) {
                    response.getWriter().write("Error: Age must be a positive number.");
                    return;
                }
            } else {
                response.getWriter().write("Error: Age is required.");
                return;
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("Error: Age must be a valid number.");
            return;
        }

        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String bloodGroup = request.getParameter("blood_group");

        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Contact: " + contact);
        System.out.println("Age: " + age);

        String[] organsArray = request.getParameterValues("organs[]");
        String organs = "";
        if (organsArray != null) {
            organs = String.join(",", organsArray);
        }
        System.out.println("Organs: " + organs);

        String sql = "INSERT INTO donors (first_name, last_name, date_of_birth, gender, aadhar_number, age, blood_group, organs, contact, email, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, dob);
            ps.setString(4, gender);
            ps.setString(5, aadhar);
            ps.setInt(6, age);
            ps.setString(7, bloodGroup);
            ps.setString(8, organs);
            ps.setString(9, contact);
            ps.setString(10, email);
            ps.setString(11, address);

            int result = ps.executeUpdate();

            if (result > 0) {
                response.getWriter().write("Success");
            } else {
                response.getWriter().write("Error: Failed to register donor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
