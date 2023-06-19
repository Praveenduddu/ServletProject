package de.zeroco.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String dateOfBirth = request.getParameter("date");
		try {
			if (!CustomerService.isContactAndEmailExist(contact, email)) {
				int age = CustomerService.getAgeWithDateOfBirth(dateOfBirth);
				CustomerPojo customer = new CustomerPojo(name, contact, email, dateOfBirth, age);
				response.getWriter().print("Hi " + name + " this is your ID :" + CustomerDAO.getIdWhenDataInserted(customer));
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerDetails.html");
				dispatcher.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
