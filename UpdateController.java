package de.zeroco.servlets;

import java.sql.Connection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		Connection connection = null;
		int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String date = request.getParameter("date");
        CustomerPojo customer = CustomerService.createObjectUsingDetails(id, name, contact, email, date);
        try {
			response.getWriter().print((CustomerDAO.updateCustomerDetails(customer) > 0) ? "Details Updated Successfully" : "Details Not Updateded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
