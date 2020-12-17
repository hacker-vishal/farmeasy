package project.farmease.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Id
	private String email;
	private String fname;
	private String lname;
	private String mobileno;
	private Integer zip;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user_email")
	private Set<Booking> booking;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn
	private Set<Address> address;

	public User(String email, String fname, String lname, String mobileno, Integer zip) {
		super();
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.mobileno = mobileno;
		this.zip = zip;
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

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public Set<Booking> getBooking() {
		return booking;
	}

	public void setBooking(Set<Booking> booking) {
		this.booking = booking;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}	
}
