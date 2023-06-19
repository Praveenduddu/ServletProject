package de.zeroco.servlets;

public class CustomerPojo {

	private int id;
	private String name;
	private String contact;
	private String email;
	private String dateOfBirth;
	private int age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CustomerPojo(String name, String contact, String email, String dateOfBirth, int age) {
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
	}
	
	public CustomerPojo(int id, String name, String contact, String email, String dateOfBirth, int age) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
	}
	
	public CustomerPojo() {
	
	}
}
