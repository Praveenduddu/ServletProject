package de.zeroco.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<CustomerPojo> customerList = (List<CustomerPojo>) request.getAttribute("listCustomers");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
        out.println("<html><body>");
        out.println("<table border=1 width=50% height=50%>");  
        out.println("<tr><th>Id</th><th>Name</th><th>Contact</th><th>Email</th><th>Date Of Birth</th><th>Age</th></tr>");
        for (CustomerPojo customer : customerList) 
        {  
        	int id = customer.getId();
            String name = customer.getName();
            String contact = customer.getContact();
            String email = customer.getEmail();
            String date = customer.getDateOfBirth();
            int age = customer.getAge();
            out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + contact + "</td><td>" + email + "</td><td>" + date + "</td><td>" + age + "</td></tr>");
        }
        out.println("</table>");  
        out.println("</body></html>");
	}
}
