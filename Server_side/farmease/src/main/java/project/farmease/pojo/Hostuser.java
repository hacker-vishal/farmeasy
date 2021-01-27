package project.farmease.pojo;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@IdClass(HostuserId.class)
public class Hostuser {

	@Id
	@Column(length=30)
	private String hostemail;
	@Id
	@Column(length=20)
	private String equipmenttype;
	@Id
	@Column(length=20)
	private String manufacturer;
	@Id
	@Column(length=20)
	private String servicetype;
	@Id
	@Column(nullable = false,length=50)
	private String location;
	private Integer rent;
	@Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] img;
	
	@ManyToOne(optional=false,fetch = FetchType.LAZY)
	@JoinColumn(name="hostemail",insertable=false, updatable=false)
	private User user;
	
	public Hostuser() {
		// TODO Auto-generated constructor stub
	}

	public Hostuser(String hostemail, String equipmenttype, String manufacturer, String servicetype, String location,
			Integer rent, byte[] img) {
		super();
		this.hostemail = hostemail;
		this.equipmenttype = equipmenttype;
		this.manufacturer = manufacturer;
		this.servicetype = servicetype;
		this.location = location;
		this.rent = rent;
		this.img = img;
	}
	
	public String getHostemail() {
		return hostemail;
	}

	public void setHostemail(String hostemail) {
		this.hostemail = hostemail;
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
}
