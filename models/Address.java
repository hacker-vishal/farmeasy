package project.farmease.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(AddressId.class)
public class Address {
    @Id
	private String email;
    @Id
    private Integer zipcode;
	private String location;
	private String city;
	private String state;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="email",insertable=false, updatable=false)
	private User user;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String email, String location, String city, String state, int zipcode) {
		super();
		this.email = email;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
}
