package project.farmease.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(nullable = false,length=30)
	private String email;
	@Column(nullable = false,length=15)
	private String fname;
	@Column(nullable = false,length=15)
	private String lname;
	@Column(nullable = false,length=15)
	private String mobileno;
	@Column(nullable = false,length=20)
	private String password;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Booking> booking;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Address> address;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Wishlist> wishlist;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private Set<Hostuser> hostuser;
	
	@OneToOne(mappedBy = "user",cascade=CascadeType.ALL)
	private Logincreds logincreds;

	public User(String email, String fname, String lname, String mobileno, String password) {
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.mobileno = mobileno;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
