package com.tap.dao;

import java.util.ArrayList;

import com.tap.model.OrderTable;

public interface OrderTableDAO 
{
	int addOrder(OrderTable o);
	ArrayList<OrderTable> getAllOrders();
//	OrderTable getOrder(String status);
	OrderTable getOrder(int orderid);
	
}
