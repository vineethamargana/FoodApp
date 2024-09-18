package com.tap.dao;

import java.util.ArrayList;

import com.tap.model.OrderHistory;

public interface OrderHistoryDAO 
{
	int addOrderHistory(OrderHistory o);
	ArrayList<OrderHistory> getAllOrderHistories();
	OrderHistory getOrderHistory(int orderhistoryid);
	ArrayList<OrderHistory> getOrderHistoryByUserId(int userid);
}
