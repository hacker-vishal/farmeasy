package project.farmease.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bookingid;
	@Column(nullable = false,length=30)
	private String email;
	@Column(nullable = false,length=30)
	private String serviceprovider;
	@Column(nullable = false,length=30)
	private String equipmenttype;
	@Column(nullable = false,length=20)
	private String manufacturer;
	@Column(nullable = false,length=20)
	private String servicetype;
	@Column(nullable = false,length=50)
	private String location;
	@Column(nullable = false)
	private Timestamp dateofbooking;
	private Timestamp datefinish;
	private Double rent;
	 
	@ManyToOne(optional=false,fetch = FetchType.LAZY)
	@JoinColumn(name="email",insertable=false, updatable=false)
	private User user;
	
	@ManyToOne
	@JoinColumns({
	@JoinColumn(name="equipmenttype",referencedColumnName = "equipmenttype",insertable=false, updatable=false),
	@JoinColumn(name="serviceprovider",referencedColumnName = "hostemail",insertable=false, updatable=false),
	@JoinColumn(name="manufacturer",referencedColumnName = "manufacturer",insertable=false, updatable=false),
	@JoinColumn(name="servicetype",referencedColumnName = "servicetype",insertable=false, updatable=false),
	@JoinColumn(name="location",referencedColumnName = "location",insertable=false, updatable=false)})
	private Hostuser hostuser;
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public Booking(Integer bookingid, String email, String serviceprovider, String equipmenttype,String manufacturer, String location, String servicetype,
			Timestamp dateofbooking, Timestamp datefinish, Double rent) {
		this.bookingid = bookingid;
		this.email = email;
		this.serviceprovider = serviceprovider;
		this.equipmenttype = equipmenttype;
		this.manufacturer = manufacturer;
		this.location = location;
		this.servicetype = servicetype;
		this.dateofbooking = dateofbooking;
		this.datefinish = datefinish;
		this.rent = rent;
	}

	public Integer getBookingid() {
		return bookingid;
	}

	public void setBookingid(Integer bookingid) {
		this.bookingid = bookingid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getServiceprovider() {
		return serviceprovider;
	}

	public void setServiceprovider(String serviceprovider) {
		this.serviceprovider = serviceprovider;
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

	public Timestamp getDateofbooking() {
		return dateofbooking;
	}

	public void setDateofbooking(Timestamp dateofbooking) {
		this.dateofbooking = dateofbooking;
	}

	public Timestamp getDatefinish() {
		return datefinish;
	}

	public void setDatefinish(Timestamp datefinish) {
		this.datefinish = datefinish;
	}

	public Double getRent() {
		return rent;
	}

	public void setRent(Double rent) {
		this.rent = rent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
