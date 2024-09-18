package com.tap.dao;

import java.util.ArrayList;

import com.tap.model.OrderItem;

public interface OrderItemDAO 
{
	int addOrderItem(OrderItem o);
	ArrayList<OrderItem> getAllOrderItems();
	OrderItem getOrderItem(int orderitemid);
	ArrayList<OrderItem> getOrderItemsByOrderId(int orderId);
}
 