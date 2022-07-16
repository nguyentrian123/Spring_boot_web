package com.laptrinhjavaweb.dto;

public class RentAreaDTO extends AbstractDTO<RentAreaDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	private Long buildingId;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	
	
}
