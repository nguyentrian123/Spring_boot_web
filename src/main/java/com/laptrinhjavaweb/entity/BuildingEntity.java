package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "street" , nullable = false)
    private String street;
    
    @Column(name = "ward", nullable = false)
    private String ward;
    
    @OneToMany(mappedBy ="building",  cascade = CascadeType.ALL) 
	private java.util.List<RentAreaEntity> rentAreas = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> users = new ArrayList<>();
    
    
    @Column(name = "districtcode", nullable = false)
    private String districtCode;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
    
    @Column(name = "floorarea")
    private Integer floorArea;
    
    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;
    
    @Column(name="rentprice")
	private Integer rentprice;
	
	@Column(name="rentpricedescription")
	private String rentpricedescription;
	
	@Column(name="servicefee")
	private String servicefee;
	
	@Column(name="carfee")
	private String carfee;
	
	@Column(name="motorbikefee")
	private String motorbikefee;
	
	@Column(name="overtimefee")
	private String overtimefee;
	
	@Column(name="waterfee")
	private String waterfee;
	
	@Column(name="electricityfee")
	private String electricityfee;
	
	@Column(name="deposit")
	private String deposit;
	
	@Column(name="payment")
	private String payment;
	
	@Column(name="renttime")
	private String renttime;
	
	@Column(name="decorationtime")
	private String decorationtime;
	
	@Column(name="managername")
	private String managerName;
	
	@Column(name="managerphone")
	private String managerPhone;
	
	@Column(name="brokeragefee")
	private String brokeragefee;
	
	@Column(name="note")
	private String note;
	
	@Column(name="linkofbuilding")
	private String linkofbuilding;
	
	@Column(name="map")
	private String map;
	
	@Column(name="image")
	private String image;
	
	
    

    public java.util.List<RentAreaEntity> getRentAreas() {
		return rentAreas;
	}

	public void setRentAreas(java.util.List<RentAreaEntity> rentAreas) {
		this.rentAreas = rentAreas;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getRentprice() {
		return rentprice;
	}

	public void setRentprice(Integer rentprice) {
		this.rentprice = rentprice;
	}

	public String getRentpricedescription() {
		return rentpricedescription;
	}

	public void setRentpricedescription(String rentpricedescription) {
		this.rentpricedescription = rentpricedescription;
	}

	public String getServicefee() {
		return servicefee;
	}

	public void setServicefee(String servicefee) {
		this.servicefee = servicefee;
	}

	public String getCarfee() {
		return carfee;
	}

	public void setCarfee(String carfee) {
		this.carfee = carfee;
	}

	public String getMotorbikefee() {
		return motorbikefee;
	}

	public void setMotorbikefee(String motorbikefee) {
		this.motorbikefee = motorbikefee;
	}

	public String getOvertimefee() {
		return overtimefee;
	}

	public void setOvertimefee(String overtimefee) {
		this.overtimefee = overtimefee;
	}

	public String getWaterfee() {
		return waterfee;
	}

	public void setWaterfee(String waterfee) {
		this.waterfee = waterfee;
	}

	public String getElectricityfee() {
		return electricityfee;
	}

	public void setElectricityfee(String electricityfee) {
		this.electricityfee = electricityfee;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getRenttime() {
		return renttime;
	}

	public void setRenttime(String renttime) {
		this.renttime = renttime;
	}

	public String getDecorationtime() {
		return decorationtime;
	}

	public void setDecorationtime(String decorationtime) {
		this.decorationtime = decorationtime;
	}

	public String getBrokeragefee() {
		return brokeragefee;
	}

	public void setBrokeragefee(String brokeragefee) {
		this.brokeragefee = brokeragefee;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLinkofbuilding() {
		return linkofbuilding;
	}

	public void setLinkofbuilding(String linkofbuilding) {
		this.linkofbuilding = linkofbuilding;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
}
