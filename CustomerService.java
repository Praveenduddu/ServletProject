package de.zeroco.servlets;

import java.util.Arrays;

import de.zeroco.util.Utility;

public class CustomerService {

	public static int getAgeWithDateOfBirth(String dateOfBirth) {
		return Utility.calculateTheAge(dateOfBirth);
	}
	
	public static CustomerPojo createObjectUsingDetails(int id, String name, String contact, String email, String date) {
		if (Utility.isBlank(name)) {
			name = CustomerDAO.getCustomerDetails(id, Arrays.asList("name")).get(0).getName();
		}
		if (Utility.isBlank(contact)) {
			contact = CustomerDAO.getCustomerDetails(id, Arrays.asList("contact")).get(0).getContact();
		}
		if (Utility.isBlank(email)) {
			email = CustomerDAO.getCustomerDetails(id, Arrays.asList("email")).get(0).getEmail();
		}
		if (Utility.isBlank(date)) {
			date = CustomerDAO.getCustomerDetails(id, Arrays.asList("date_of_birth")).get(0).getDateOfBirth();
		}
		return new CustomerPojo(id, name, contact, email, date, getAgeWithDateOfBirth(date));
	}
	
	public static boolean isContactAndEmailExist(String contact, String email) {
		if (Utility.isBlank(CustomerDAO.getDetailsByColumn("contact", contact)) && Utility.isBlank(CustomerDAO.getDetailsByColumn("email", email))) {
			return false;
		}
		return true;
	}
}
