package com.laptrinhjavaweb.dto.request;

public class UserAssign {

	private Long buildingId;
	private Long[] staffs;
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Long[] getStaffs() {
		return staffs;
	}
	public void setStaffs(Long[] staffs) {
		this.staffs = staffs;
	}
	
	
}
