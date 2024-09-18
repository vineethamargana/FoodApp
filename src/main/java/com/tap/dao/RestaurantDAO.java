package com.tap.dao;

import java.util.ArrayList;

import com.tap.model.Restaurant;

public interface RestaurantDAO 
{
	int addRestaurant(Restaurant r);
	ArrayList<Restaurant> getAllRestaurants();
	Restaurant getRestaurant(int restaurantid);
	int updateRestaurant(Restaurant r);
//	int deleteRestaurant(int restaurantid);
	int deleteRestaurant(String restaurantname);

}
