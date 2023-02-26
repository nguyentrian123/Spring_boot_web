package com.laptrinhjavaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {

	private String name ; 
	private String street;
	private String ward ; 
	private String district;
	private Integer numberOfBasement;
	private String direction;
	private String level ;
	private Integer rentPriceTo;
	private Integer rentPriceFrom;
	private String managerName;
	private String managerPhone;
	private Long staff;
	private Integer floorArea;
	private List<String> renttype;
	private Integer rentAreaTo;
	private Integer rentAreaFrom;
	
	
	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getWard() {
		return ward;
	}

	public String getDistrict() {
		return district;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public Integer getRentPriceTo() {
		return rentPriceTo;
	}

	public Integer getRentPriceFrom() {
		return rentPriceFrom;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public Long getStaff() {
		return staff;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public List<String> getRenttype() {
		return renttype;
	}

	public Integer getRentAreaTo() {
		return rentAreaTo;
	}

	public Integer getRentAreaFrom() {
		return rentAreaFrom;
	}
	
	
	
	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.street = builder.street;
		this.ward = builder.ward;
		this.district = builder.district;
		this.numberOfBasement = builder.numberOfBasement;
		this.direction = builder.direction;
		this.level = builder.level;
		this.rentPriceTo = builder.rentPriceTo;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.managerName = builder.managerName;
		this.managerPhone = builder.managerPhone;
		this.staff = builder.staff;
		this.floorArea = builder.floorArea;
		this.renttype = builder.renttype;
		this.rentAreaTo = builder.rentAreaTo;
		this.rentAreaFrom = builder.rentAreaFrom;
	}



	public static class Builder {

		private String name ; 
		private String street;
		private String ward ; 
		private String district;
		private Integer numberOfBasement;
		private String direction;
		private String level ;
		private Integer rentPriceTo;
		private Integer rentPriceFrom;
		private String managerName;
		private String managerPhone;
		private Long staff;
		private Integer floorArea;
		private List<String> renttype;
		private Integer rentAreaTo;
		private Integer rentAreaFrom;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}


		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}


		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}


		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}


		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}


		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}


		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}


		public Builder setRentPriceTo(Integer rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}


		public Builder setRentPriceFrom(Integer rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}


		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}


		public Builder setManagerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}


		public Builder setStaff(Long staff) {
			this.staff = staff;
			return this;
		}


		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}


		public Builder setRenttype(List<String> renttype) {
			this.renttype = renttype;
			return this;
		}


		public Builder setRentAreaTo(Integer rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}


		public Builder setRentAreaFrom(Integer rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}   

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this); // sau khi set value xong thì gọi hàm build để trả lại cái Builder mới có value
		}
	}
	
	
}
