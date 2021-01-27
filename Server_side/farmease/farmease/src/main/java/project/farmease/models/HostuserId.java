package project.farmease.models;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class HostuserId implements Serializable {

	private String email;
	private String equipmenttype;
	private String manufacturer;
	private String servicetype;

	public HostuserId(String email, String equipmenttype, String manufacturer, String servicetype) {
		super();
		this.email = email;
		this.equipmenttype = equipmenttype;
		this.manufacturer = manufacturer;
		this.servicetype = servicetype;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) 
        	return false;
        HostuserId hostuserId = (HostuserId) o;
        return email.equals(hostuserId.email) &&
                equipmenttype.equals(hostuserId.equipmenttype) &&
                manufacturer.equals(hostuserId.manufacturer) &&
                servicetype.equals(hostuserId.servicetype);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email,equipmenttype,manufacturer,servicetype);
	}
}
