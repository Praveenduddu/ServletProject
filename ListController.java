package de.zeroco.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		List<CustomerPojo> listCustomers = CustomerDAO.listCustomers();
		request.setAttribute("listCustomers", listCustomers);
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("view");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
