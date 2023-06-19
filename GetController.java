package de.zeroco.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.jdbc.DBUtil;
import de.zeroco.util.Utility;

public class GetController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			List<CustomerPojo> listCustomers = CustomerDAO.getCustomerDetails(id);
			request.setAttribute("listCustomers", listCustomers);
			RequestDispatcher dispatcher = request.getRequestDispatcher("view");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
