package project.farmease.dto;

public class Userdto {
	
	private String email, password;

	public Userdto() {
		
		
	}

	public Userdto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
}
