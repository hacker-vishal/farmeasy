package project.farmease.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Logincreds {

	@Id
	@Column(nullable=false,length=30)
	private String email;
	@Column(nullable=false,length=20)
	private String password;
	@Column(nullable=true,length=6)
	private Integer otp;
	
	@OneToOne(optional=false,fetch = FetchType.LAZY)
	@JoinColumn(name = "email",insertable=false, updatable=false)
	private User user;
	
	public Logincreds() {
		// TODO Auto-generated constructor stub
	}

	public Logincreds(String email, String password, Integer otp) {
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
}
