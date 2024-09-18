package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.dao.OrderItemDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    private ArrayList<OrderItem> orderItemList = new ArrayList<>();
    
    // SQL Queries
    private static final String ADD_ORDER_ITEM = "INSERT INTO `orderitem` (`orderid`, `menuid`, `quantity`, `itemtotal`) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM `orderitem`";
    private static final String SELECT_ON_ID = "SELECT * FROM `orderitem` WHERE `orderitemid` = ?";
    private static final String SELECT_BY_ORDER_ID = "SELECT * FROM `orderitem` WHERE `orderid` = ?"; // New query for orderID

    
    // Constructor
    public OrderItemDAOImpl() {
        try {
            con = DBUtils.myconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrderItem(OrderItem o) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(ADD_ORDER_ITEM);
            pstmt.setInt(1, o.getOrderid());
            pstmt.setInt(2, o.getMenuid());
            pstmt.setInt(3, o.getQuantity());
            pstmt.setDouble(4, o.getSubtotal());
            status = pstmt.executeUpdate(); // executeUpdate for insert operation
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ArrayList<OrderItem> getAllOrderItems() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            orderItemList = extractOrderItemFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
 // New method to get order items by order ID
    public ArrayList<OrderItem> getOrderItemsByOrderId(int orderId) {
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(SELECT_BY_ORDER_ID);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            orderItemList = extractOrderItemFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public OrderItem getOrderItem(int orderitemid) {
        OrderItem orderItem = null;
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, orderitemid);
            resultSet = pstmt.executeQuery(); // executeQuery for select operation
            ArrayList<OrderItem> orderItems = extractOrderItemFromResultSet(resultSet);
            if (!orderItems.isEmpty()) {
                orderItem = orderItems.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    private ArrayList<OrderItem> extractOrderItemFromResultSet(ResultSet resultSet) {
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem(
                    resultSet.getInt("orderitemid"),
                    resultSet.getInt("orderid"),
                    resultSet.getInt("menuid"),
                    resultSet.getInt("quantity"),
                    resultSet.getDouble("itemtotal")
                );
                orderItemList.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
}
