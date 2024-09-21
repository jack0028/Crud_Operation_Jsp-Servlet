package net.javaguides.usermanagement.model;

public class User {
	protected int id;
    protected String name;
    protected String email;
    protected String contact;
    protected int otp;
    protected String country;
    
    
	public User() {}
	public User(String name,  String contact, int otp, String email, String country) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.otp = otp;
		this.country = country;
	}
	public User(int id, String name, String email, String contact, int otp,  String country) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.otp = otp;
		this.email = email;
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}

}
