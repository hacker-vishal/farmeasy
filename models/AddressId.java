package project.farmease.models;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class AddressId implements Serializable{

	private String email;
	private Integer zipcode;
	
	public AddressId() {
		// TODO Auto-generated constructor stub
	}

	public AddressId(String email, int zipcode) {
		super();
		this.email = email;
		this.zipcode = zipcode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) 
        	return false;
        AddressId addressId = (AddressId) o;
        return email.equals(addressId.email) &&
                zipcode.equals(addressId.zipcode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email,zipcode);
	}
	
	
}
