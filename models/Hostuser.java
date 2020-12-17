package project.farmease.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@IdClass(HostuserId.class)
public class Hostuser {

	private String email;
	private String equipmenttype;
	private String manufacturer;
	private String servicetype;
	private String location;
	private Integer rent;
	@Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] img;
	
	@ManyToOne
	private User user;
	
	public Hostuser() {
		// TODO Auto-generated constructor stub
	}

	public Hostuser(String email, String equipmenttype, String manufacturer, String servicetype, String location,
			Integer rent, byte[] img) {
		super();
		this.email = email;
		this.equipmenttype = equipmenttype;
		this.manufacturer = manufacturer;
		this.servicetype = servicetype;
		this.location = location;
		this.rent = rent;
		this.img = img;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getRent() {
		return rent;
	}

	public void setRent(Integer rent) {
		this.rent = rent;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
