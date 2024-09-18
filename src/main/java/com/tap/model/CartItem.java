package com.tap.model;

public class CartItem {
	private int itemId;
	private int restaurantId;
	private String name;
	private double price;
	private int quantity;
	private double subtotal;
	public CartItem() {
		super();
	}
	public CartItem(int itemId, int restaurantId, String name, double price, int quantity, double subtotal) {
		super();
		this.itemId = itemId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
        this.subtotal = price * quantity; // Calculate subtotal initially
	}
	public CartItem(int restaurantId, String name, double price, int quantity, double subtotal) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		// Recalculate subtotal whenever the price is updated
        this.subtotal = this.price * this.quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		 // Recalculate subtotal whenever the quantity is updated
        this.subtotal = this.price * this.quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", restaurantId=" + restaurantId + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}
}
