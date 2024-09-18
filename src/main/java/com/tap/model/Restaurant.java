package com.tap.model;

public class Restaurant {
   private int restaurantid;
   private String restaurantname;
   private int deliverytime;
   private String cuisiontype;
   private String address;
   private double ratings;
   private boolean isActive;
   private int adminuserId;
   private String imgpath;
   
   public Restaurant() {
	super();
   }

   public Restaurant(int restaurantid, String restaurantname, int deliverytime, String cuisiontype, String address,
		double ratings, boolean isActive, int adminuserId, String imgpath) {
	    super();
		this.restaurantid = restaurantid;
		this.restaurantname = restaurantname;
		this.deliverytime = deliverytime;
		this.cuisiontype = cuisiontype;
		this.address = address;
		this.ratings = ratings;
		this.isActive = isActive;
		this.adminuserId = adminuserId;
		this.imgpath = imgpath;
   }
   public Restaurant(String restaurantname, int deliverytime, String cuisiontype, String address,
			float ratings, boolean isActive, int adminId, String imgpath) {
		    super();
			this.restaurantname = restaurantname;
			this.deliverytime = deliverytime;
			this.cuisiontype = cuisiontype;
			this.address = address;
			this.ratings = ratings;
			this.isActive = isActive;
			this.adminuserId = adminId;
			this.imgpath = imgpath;
	   }
   public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getRestaurantname() {
		return restaurantname;
	}

	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}

	public int getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(int deliverytime) {
		this.deliverytime = deliverytime;
	}

	public String getCuisiontype() {
		return cuisiontype;
	}

	public void setCuisiontype(String cuisiontype) {
		this.cuisiontype = cuisiontype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getAdminId() {
		return adminuserId;
	}

	public void setAdminId(int adminuserId) {
		this.adminuserId = adminuserId;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantid=" + restaurantid + ", restaurantname=" + restaurantname + ", deliverytime="
				+ deliverytime + ", cuisiontype=" + cuisiontype + ", address=" + address + ", ratings=" + ratings
				+ ", isActive=" + isActive + ", adminId=" + adminuserId + ", imgpath=" + imgpath + "]";
	}   
}
