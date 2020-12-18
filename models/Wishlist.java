package project.farmease.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(WishlistId.class)
public class Wishlist {

	@Id
	private String email;
	@Id
	private String equipmenttype;
	@Id
	private String servicetype;
	@Id
	private String serviceprovider;
	private Integer rent;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="email",insertable=false, updatable=false)
	private User user;
	
	public Wishlist() {
		// TODO Auto-generated constructor stub
	}

	public Wishlist(String email, String equipmenttype, String servicetype, String serviceprovider, Integer rent) {
		super();
		this.email = email;
		this.equipmenttype = equipmenttype;
		this.servicetype = servicetype;
		this.serviceprovider = serviceprovider;
		this.rent = rent;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEquipmenttype() {
		return equipmenttype;
	}

	public void setEquipmenttype(String equipmenttype) {
		this.equipmenttype = equipmenttype;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getServiceprovider() {
		return serviceprovider;
	}

	public void setServiceprovider(String serviceprovider) {
		this.serviceprovider = serviceprovider;
	}

	public Integer getRent() {
		return rent;
	}

	public void setRent(Integer rent) {
		this.rent = rent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
