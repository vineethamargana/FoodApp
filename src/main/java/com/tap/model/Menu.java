package com.tap.model;

public class Menu {
   private int menuid;
   private int restaurantid;
   private String itemName;
   private double price;
   private String description;
   private boolean isAvailable;
   private String imgpath;
   
   public Menu() {
		super();
	}
	
   public Menu(int menuid, int restaurantid, String itemname, double price, String description, boolean isAvailable,String imgpath) {
		super();
		this.menuid = menuid;
		this.restaurantid = restaurantid;
		this.itemName = itemname;
		this.price = price;
		this.description = description;
		this.isAvailable = isAvailable;
		this.imgpath = imgpath;
	}
   public Menu(int restaurantid, String itemname, double price, String description, boolean isAvailable,String imgpath) {
		super();
		this.restaurantid = restaurantid;
		this.itemName = itemname;
		this.price = price;
		this.description = description;
		this.isAvailable = isAvailable;
		this.imgpath = imgpath;
	}
	
   public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public int getRestaurantid() {
		return restaurantid;
	}
	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemname(String itemname) {
		this.itemName = itemname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	
	@Override
	public String toString() {
		return "Menu [menuid=" + menuid + ", restaurantid=" + restaurantid + ", menuname=" + itemName + ", price=" + price
				+ ", description=" + description + ", isAvailable=" + isAvailable +  ", imgpath=" + imgpath + "]";
	}	
}
