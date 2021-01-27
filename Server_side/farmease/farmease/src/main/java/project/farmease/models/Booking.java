package project.farmease.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
public class Booking {

	@Id
	private Integer bookingid;
	private String email;
	private String serviceprovider;
	private String equipmenttype;
	private String servicetype;
	private Timestamp dateofbooking;
	private Timestamp datefinish;
	private Integer rent;
	 
	@ManyToOne
	private User user;
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public Booking(Integer bookingid, String email, String serviceprovider, String equipmenttype, String servicetype,
			Timestamp dateofbooking, Timestamp datefinish, Integer rent, User user) {
		super();
		this.bookingid = bookingid;
		this.email = email;
		this.serviceprovider = serviceprovider;
		this.equipmenttype = equipmenttype;
		this.servicetype = servicetype;
		this.dateofbooking = dateofbooking;
		this.datefinish = datefinish;
		this.rent = rent;
		this.user = user;
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
