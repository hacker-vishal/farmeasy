package project.farmease.dto;

public class UserProfile {

	public UserProfile() {
		// TODO Auto-generated constructor stub
	}

	private String email;
	private String fname;
	private String lname;
	private String mobileno;
	private String location;
	private String city;
	private String state;
	private int zipcode;
	
	public UserProfile(String email, String fname, String lname, String mobileno, String location, String city,
			String state, int zipcode) {
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.mobileno = mobileno;
		this.location = location;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
}
