package project.farmease.models;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class HostuserId implements Serializable {

	private String hostemail;
	private String equipmenttype;
	private String manufacturer;
	private String servicetype;

	public HostuserId() {
		// TODO Auto-generated constructor stub
	}

	public HostuserId(String hostemail, String equipmenttype, String manufacturer, String servicetype) {
		super();
		this.hostemail = hostemail;
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
        return hostemail.equals(hostuserId.hostemail) &&
                equipmenttype.equals(hostuserId.equipmenttype) &&
                manufacturer.equals(hostuserId.manufacturer) &&
                servicetype.equals(hostuserId.servicetype);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hostemail,equipmenttype,manufacturer,servicetype);
	}
}
