package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.dao.OrderTableDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.OrderTable;

public class OrderTableDAOImpl implements OrderTableDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    private ArrayList<OrderTable> orderList = new ArrayList<>();
    
    // SQL Queries
    private static final String ADD_ORDER = "INSERT INTO `order_table` (`restaurantid`, `userid`, `orderDate`, `totalamount`, `status`, `paymentmode`) "
    	                                    	+ "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM `order_table`";
    private static final String SELECT_ON_ID = "SELECT * FROM `order_table` WHERE `orderid` = ?";
    
    // Constructor
    public OrderTableDAOImpl() {
        try {
            con = DBUtils.myconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrder(OrderTable o) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(ADD_ORDER);
            pstmt.setInt(1, o.getRestaurantid());
            pstmt.setInt(2, o.getUserid());
            pstmt.setDate(3, new java.sql.Date(o.getOrderDate().getTime())); // Convert java.util.Date to java.sql.Date
            pstmt.setDouble(4, o.getTotalamount());
            pstmt.setString(5, o.getStatus());
            pstmt.setString(6, o.getPaymentmode());
            status = pstmt.executeUpdate(); // executeUpdate for insert operation
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ArrayList<OrderTable> getAllOrders() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            orderList = extractOrderFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public OrderTable getOrder(int orderid) {
        OrderTable order = null;
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, orderid);
            resultSet = pstmt.executeQuery(); // executeQuery for select operation
            ArrayList<OrderTable> orders = extractOrderFromResultSet(resultSet);
            if (!orders.isEmpty()) {
                order = orders.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    private ArrayList<OrderTable> extractOrderFromResultSet(ResultSet resultSet) {
        ArrayList<OrderTable> orderList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                OrderTable order = new OrderTable(
                    resultSet.getInt("orderid"),
                    resultSet.getInt("restaurantid"),
                    resultSet.getInt("userid"),
                    resultSet.getDate("orderDate"),
                    resultSet.getDouble("totalamount"),
                    resultSet.getString("status"),
                    resultSet.getString("paymentmode")
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
