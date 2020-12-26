package project.farmease.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
@IdClass(WishlistId.class)
public class Wishlist {

	@Id
	@Column(nullable = false,length=30)
	private String email;
	@Id
	@Column(nullable = false,length=20)
	private String equipmenttype;
	@Id
	@Column(nullable = false,length=20)
	private String servicetype;
	@Id
	@Column(nullable = false,length=20)
	private String serviceprovider;
	@Column(nullable = false,length=30)
	private String manufacturer;
	private Integer rent;
	
	@ManyToOne(optional=false,fetch = FetchType.LAZY)
	@JoinColumn(name="email",insertable=false, updatable=false)
	private User user;
	
	@ManyToOne
	@JoinColumns({
	@JoinColumn(name="equipmenttype",referencedColumnName = "equipmenttype",insertable=false, updatable=false),
	@JoinColumn(name="serviceprovider",referencedColumnName = "hostemail",insertable=false, updatable=false),
	@JoinColumn(name="manufacturer",referencedColumnName = "manufacturer",insertable=false, updatable=false),
	@JoinColumn(name="servicetype",referencedColumnName = "servicetype",insertable=false, updatable=false)})
	private Hostuser hostuser;
	
	public Wishlist() {
		// TODO Auto-generated constructor stub
	}

	public Wishlist(String email, String equipmenttype,String manufacturer, String servicetype, String serviceprovider, Integer rent) {
		super();
		this.email = email;
		this.equipmenttype = equipmenttype;
		this.manufacturer = manufacturer;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Hostuser getHostuser() {
		return hostuser;
	}

	public void setHostuser(Hostuser hostuser) {
		this.hostuser = hostuser;
	}
}
