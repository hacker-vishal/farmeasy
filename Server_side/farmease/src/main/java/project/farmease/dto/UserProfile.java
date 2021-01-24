package project.farmease.dto;

public class UserProfile {

	public UserProfile() {
		// TODO Auto-generated constructor stub
	}

	private String email;
	private String fname;
	private String lname;
	private String mobileno;
	
	public UserProfile(String email, String fname, String lname, String mobileno) {
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.mobileno = mobileno;
		
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
	

}
