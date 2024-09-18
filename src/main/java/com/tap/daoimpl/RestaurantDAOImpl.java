package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.dao.RestaurantDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {

    Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
    Restaurant restaurant;

    // SQL Queries
    private static final String ADD_RESTAURANT = "INSERT INTO `restaurant` (`restaurantname`, `deliverytime`, `cuisiontype`, `address`, `ratings`,"
    		                                     + " `isActive`, `adminuserId`, `imgpath`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM `restaurant`";
    private static final String SELECT_ON_ID = "SELECT * FROM `restaurant` WHERE `restaurantid` = ?";
    private static final String UPDATE_ON_ID = "UPDATE `restaurant` SET `restaurantname` = ?, `deliverytime` = ?, `cuisiontype` = ?, `address` = ?, "
    	                                    	+ "`ratings` = ?, `isActive` = ?, `adminuserId` = ?, `imgpath` = ? WHERE `restaurantid` = ?";
    private static final String DELETE_ON_ID = "DELETE FROM `restaurant` WHERE `restaurantid` = ?";

    int status = 0;

    public RestaurantDAOImpl() {
        try {
            con = DBUtils.myconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addRestaurant(Restaurant r) {
        try 
        {
            pstmt = con.prepareStatement(ADD_RESTAURANT);
            pstmt.setString(1, r.getRestaurantname());
            pstmt.setInt(2, r.getDeliverytime());
            pstmt.setString(3, r.getCuisiontype());
            pstmt.setString(4, r.getAddress());
            pstmt.setDouble(5, r.getRatings());
            pstmt.setBoolean(6, r.isActive());
            pstmt.setInt(7, r.getAdminId());
            pstmt.setString(8, r.getImgpath());

            status = pstmt.executeUpdate(); // executeUpdate because we are using insert command
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return status;
    }

   
    
    @Override
    public ArrayList<Restaurant> getAllRestaurants() { //it will fetch all the restaurants and add it into a list and give the list
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            restaurantList = extractRestaurantFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public Restaurant getRestaurant(int restaurantid) 
    {
        try 
        {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, restaurantid);
            resultSet = pstmt.executeQuery(); // executeQuery because we are using select command
            restaurantList = extractRestaurantFromResultSet(resultSet);
            if (!restaurantList.isEmpty()) {
                restaurant = restaurantList.get(0);
               }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public int updateRestaurant(Restaurant r) {
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ID);
            pstmt.setString(1, r.getRestaurantname());
            pstmt.setInt(2, r.getDeliverytime());
            pstmt.setString(3, r.getCuisiontype());
            pstmt.setString(4, r.getAddress());
            pstmt.setDouble(5, r.getRatings());
            pstmt.setBoolean(6, r.isActive());
            pstmt.setInt(7, r.getAdminId());
            pstmt.setString(8, r.getImgpath());
            pstmt.setInt(9, r.getRestaurantid());

            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteRestaurant(String restaurantname) {
        try {
            pstmt = con.prepareStatement(DELETE_ON_ID);
            pstmt.setString(1, restaurantname);

            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    private ArrayList<Restaurant> extractRestaurantFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                restaurantList.add(new Restaurant(
                    resultSet.getInt("restaurantid"),
                    resultSet.getString("restaurantname"),
                    resultSet.getInt("deliverytime"),
                    resultSet.getString("cuisiontype"),
                    resultSet.getString("address"),
                    resultSet.getDouble("ratings"),
                    resultSet.getBoolean("isActive"),
                    resultSet.getInt("adminuserId"),
                    resultSet.getString("imgpath")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }
}

