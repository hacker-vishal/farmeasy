package project.farmease.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;

@SuppressWarnings("serial")
public class WishlistId implements Serializable{

	@Id
	private String email;
	@Id
	private String equipmenttype;
	@Id
	private String servicetype;
	@Id
	private String serviceprovider;
	
	public WishlistId() {
		// TODO Auto-generated constructor stub
	}

	public WishlistId(String email, String equipmenttype, String servicetype, String serviceprovider) {
		super();
		this.email = email;
		this.equipmenttype = equipmenttype;
		this.servicetype = servicetype;
		this.serviceprovider = serviceprovider;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) 
        	return false;
        WishlistId wishlistId = (WishlistId) o;
        return email.equals(wishlistId.email) &&
                equipmenttype.equals(wishlistId.equipmenttype) &&
                servicetype.equals(wishlistId.servicetype) &&
                serviceprovider.equals(wishlistId.serviceprovider);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(email,equipmenttype,servicetype,serviceprovider);
	}
}
