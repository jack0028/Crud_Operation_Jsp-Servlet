package net.javaguides.registration.model;

public class Employee {
	private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String address;
    private String contact;

    public Employee(int id,String firstName, String lastName, String username, String address,
			String contact) {
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		//this.password = password;
		this.address = address;
		this.contact = contact;
	}


	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
