package com.tap.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
 
	// The cart items, stored as a map of item IDs to CartItem objects
	private Map<Integer, CartItem> items;
	
	public Cart() {
		this.items = new HashMap<>();
	}
	
	// Add an item to the cart
	public void addItem(CartItem item) {
		int itemId = item.getItemId();
		if (items.containsKey(itemId)) {
			// If item already exists, increase the quantity
			CartItem existingItem = items.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
		} else {
			items.put(itemId, item);
		}
	}
	
	// Update the quantity of an item in the cart
	public void updateItem(int itemId, int quantity) {
		if (items.containsKey(itemId)) {
			if (quantity <= 0) {
				items.remove(itemId); // Remove the item if quantity is 0 or less
			} else {
				CartItem cartItem = items.get(itemId);
				cartItem.setQuantity(quantity); // Update the quantity of the item
			}
		}
	}
	
	// Remove an item from the cart
	public void removeItem(int itemId) {
		items.remove(itemId);
	}
	
	// Get all items in the cart
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	
	// Clear the cart
	public void clearItems() {
		items.clear();
	}
	
	// Get the restaurant ID (assuming all items are from the same restaurant)
	public int getRestaurantId() {
		if (!items.isEmpty()) {
			return items.values().iterator().next().getRestaurantId();
		}
		return -1; // or throw an exception if the cart is empty
	}
	
	// Calculate the total amount of all items in the cart
	public double getTotalAmount() {
		double total = 0.0;
		for (CartItem item : items.values()) {
			total += item.getSubtotal();
		}
		return total;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cart contains ").append(items.size()).append(" items:\n");
		for (CartItem item : items.values()) {
			sb.append(item.toString()).append("\n");
		}
		sb.append("Total amount: ").append(getTotalAmount());
		return sb.toString();
	}
	

    

}
