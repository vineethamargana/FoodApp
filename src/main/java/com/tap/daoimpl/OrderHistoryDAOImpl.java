package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.dao.OrderHistoryDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO 
{
    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    
    // SQL Queries
    private static final String ADD_ORDER_HISTORY = "INSERT INTO `order_history` (`orderid`, `userid`, `totalamount`, `status`) VALUES (?, ?, ?, ?)";
    
    private static final String SELECT_ALL = "SELECT * FROM `order_history`";
    
    private static final String SELECT_ON_ID = "SELECT * FROM `order_history` WHERE `orderhistoryid` = ?";
  
    private static final String SELECT_ON_USERID = "SELECT * FROM orderhistory WHERE userid = ?";

    // Constructor
    public OrderHistoryDAOImpl() {
        try {
            con = DBUtils.myconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrderHistory(OrderHistory o) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(ADD_ORDER_HISTORY);
            pstmt.setInt(1, o.getOrderid());
            pstmt.setInt(2, o.getUserid());
            pstmt.setFloat(3, o.getTotalamount());
            pstmt.setString(4, o.getStatus());
            status = pstmt.executeUpdate(); // executeUpdate for insert operation
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

   
    
    @Override
    public ArrayList<OrderHistory> getAllOrderHistories() {
        ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

   
    @Override
    public OrderHistory getOrderHistory(int orderhistoryid) {
        OrderHistory orderHistory = null;
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, orderhistoryid);
            resultSet = pstmt.executeQuery(); // executeQuery for select operation
            ArrayList<OrderHistory> orderHistories = extractOrderHistoryFromResultSet(resultSet);
            if (!orderHistories.isEmpty()) {
                orderHistory = orderHistories.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }
    public ArrayList<OrderHistory> getOrderHistoryByUserId(int userId)
    {
    	   ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
           try {
        	   pstmt = con.prepareStatement(SELECT_ON_USERID);
               pstmt.setInt(1, userId);
               resultSet = pstmt.executeQuery();
               orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return orderHistoryList;

    }

    private ArrayList<OrderHistory> extractOrderHistoryFromResultSet(ResultSet resultSet) {
        ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                OrderHistory orderHistory = new OrderHistory(
                    resultSet.getInt("orderhistoryid"),
                    resultSet.getInt("orderid"),
                    resultSet.getInt("userid"),
                    resultSet.getDate("orderdate"),
                    resultSet.getFloat("totalamount"),
                    resultSet.getString("status")
                );
                orderHistoryList.add(orderHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}
