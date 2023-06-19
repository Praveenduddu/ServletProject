package de.zeroco.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import de.zeroco.jdbc.DBUtil;
import de.zeroco.jdbc.JavaDataBaseConnection;
import de.zeroco.jdbc.QueryBuilder;
import de.zeroco.util.Utility;

public class CustomerDAO {

	public static final String SCHEMA = "zerocode";
	public static final String TABLE_NAME = "customer";
	public static final String[] COLUMNS = {"name", "contact", "email", "date_of_birth", "age"};
	public static final String CONDITION_COLUMN = "pk_id";
	public static final String ASSIGN_OPERATOR = "=";
	
	public static int getIdWhenDataInserted(CustomerPojo customer) {
		List<Object> values = getListFromObject(customer);
		int genereatedKey = 0;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, CustomerDAO.SCHEMA);
			genereatedKey = DBUtil.getGeneratedKey(SCHEMA, TABLE_NAME, Arrays.asList(COLUMNS), values, connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			Utility.closeConnection(connection);
		}
		return genereatedKey;
	}
	
	public static List<CustomerPojo> listCustomers() {
		List<CustomerPojo> users = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, SCHEMA);
			Statement statement = connection.createStatement();
			users = getResultSetIntoList(statement.executeQuery(QueryBuilder.getListQuery(SCHEMA, TABLE_NAME, Arrays.asList())));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utility.closeConnection(connection);
		}
		return users;
	}
	
	public static int updateCustomerDetails(CustomerPojo customer) {
		List<Object> values = getListFromObject(customer);
		Connection connection = null;
		int numOfRowsUpdate = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, SCHEMA);
			numOfRowsUpdate = DBUtil.update(SCHEMA, TABLE_NAME, Arrays.asList(COLUMNS), values, CustomerDAO.CONDITION_COLUMN, customer.getId(), connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return numOfRowsUpdate;
	}
	
	public static int deleteCustomerDetails(int id) {
		Connection connection = null;
		int numOfRowsUpdate = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, SCHEMA);
			numOfRowsUpdate = DBUtil.delete(SCHEMA, TABLE_NAME, "pk_id", id, connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return numOfRowsUpdate;
	}
	
	public static List<CustomerPojo> getCustomerDetails(int id, List<String> list) {
		List<CustomerPojo> users = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, CustomerDAO.SCHEMA);
			statement = JavaDataBaseConnection.getPreparedStatement(connection,  QueryBuilder.getQuery(SCHEMA, TABLE_NAME, list, CustomerDAO.CONDITION_COLUMN, CustomerDAO.ASSIGN_OPERATOR));
			statement.setObject(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.getMetaData().getColumnCount() > 1) {
				users = getResultSetIntoList(resultSet);
			} else {
				users.add(getResultSetIntoObject(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utility.closeConnection(connection);
		}
		return users;
	}
	
	public static List<CustomerPojo> getCustomerDetails(int id) {
		return getCustomerDetails(id, Arrays.asList());
	}
	
	public static List<Object> getListFromObject(CustomerPojo customer) {
		List<Object> values = new ArrayList<Object>();
		values.add(customer.getName());
		values.add(customer.getContact());
		values.add(customer.getEmail());
		values.add(customer.getDateOfBirth());
		values.add(customer.getAge());
		return values;
	}
	
	public static List<CustomerPojo> getResultSetIntoList(ResultSet resultSet) {
		List<CustomerPojo> users = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("pk_id");
				String name = resultSet.getString("name");
				String contact = resultSet.getString("contact");
				String email = resultSet.getString("email");
				String date = resultSet.getString("date_of_birth");
				int age = resultSet.getInt("age");
				users.add(new CustomerPojo(id, name, contact, email, date, age));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static CustomerPojo getResultSetIntoObject(ResultSet resultSet) {
		CustomerPojo customer = new CustomerPojo();
		try {
			while (resultSet.next()) {
				if (resultSet.getMetaData().getColumnName(1) == "name") {
					customer.setName(resultSet.getString("name"));
				} else if (resultSet.getMetaData().getColumnName(1).equals("contact")) {
					customer.setContact(resultSet.getString("contact"));
				} else if (resultSet.getMetaData().getColumnName(1).equals("email")) {
					customer.setEmail(resultSet.getString("email"));
				} else if (resultSet.getMetaData().getColumnName(1).equals("date_of_birth")) {
					customer.setDateOfBirth(resultSet.getString("date_of_birth"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public static Object getDetailsByColumn(String column, String value) {
		List<Map<String, Object>> list = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, CustomerDAO.SCHEMA);
			list = DBUtil.get(SCHEMA, TABLE_NAME, Arrays.asList(), column, ASSIGN_OPERATOR, value, connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			Utility.closeConnection(connection);
		}
		return list;
	}
}
