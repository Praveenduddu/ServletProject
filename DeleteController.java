package de.zeroco.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeroco.jdbc.DBUtil;
import de.zeroco.util.Utility;

public class DeleteController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			response.getWriter().print((CustomerDAO.deleteCustomerDetails(id)) > 0 ? "Deleted Successfully" : "Details Not Deleted");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
