package project.farmease.dto;

public class Hostdto {

	private String equipmenttype,location;

	public Hostdto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hostdto(String equipmenttype, String location) {
		super();
		this.equipmenttype = equipmenttype;
		this.location = location;
	}

	public String getEquipmenttype() {
		return equipmenttype;
	}

	public void setEquipmenttype(String equipmenttype) {
		this.equipmenttype = equipmenttype;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
