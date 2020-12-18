package project.farmease.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Logincreds {

	@Id
	private String email;
	private String password;
	private Integer otp;
	
	@OneToOne
	@JoinColumn(name = "email")
	private User user;
	
	public Logincreds() {
		// TODO Auto-generated constructor stub
	}

	public Logincreds(String email, String password, Integer otp) {
		super();
		this.email = email;
		this.password = password;
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
