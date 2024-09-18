package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.dao.MenuDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.Menu;

public class MenuDAOImpl implements MenuDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    private ArrayList<Menu> menuList = new ArrayList<>();
    private Menu menu;

    private static final String ADD_MENU = "INSERT INTO `menu` (`restaurantid`, `itemName`, `price`, `description`, `isAvailable`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM `menu`";
    private static final String SELECT_ON_ID = "SELECT * FROM `menu` WHERE `menuid` = ?";
    private static final String UPDATE_ON_ID = "UPDATE `menu` SET `restaurantid` = ?, `itemName` = ?, `price` = ?, `description` = ?, `isAvailable` = ? "
    		                                   + "WHERE `menuid` = ?";
    private static final String DELETE_ON_ID = "DELETE FROM `menu` WHERE `menuid` = ?";
    private static final String SELECT_ON_RESID = "SELECT * FROM `menu` WHERE `restaurantid` = ?";

    public MenuDAOImpl() {
        try {
        	 con = DBUtils.myconnect();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    @Override
    public int addMenu(Menu m) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(ADD_MENU);
            pstmt.setInt(1, m.getRestaurantid());
            pstmt.setString(2, m.getItemName());
            pstmt.setDouble(3, m.getPrice());
            pstmt.setString(4, m.getDescription());
            pstmt.setBoolean(5, m.isAvailable());
            status = pstmt.executeUpdate(); // executeUpdate for insert operation
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ArrayList<Menu> getAllMenus() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            menuList = extractMenuFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public Menu getMenu(int itemId) {
        menu = null;
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, itemId);
            resultSet = pstmt.executeQuery(); // executeQuery for select operation
            ArrayList<Menu> menus = extractMenuFromResultSet(resultSet);
            if (!menus.isEmpty()) {
                menu = menus.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public int updateMenu(Menu m) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(UPDATE_ON_ID);
            pstmt.setInt(1, m.getRestaurantid());
            pstmt.setString(2, m.getItemName());
            pstmt.setDouble(3, m.getPrice());
            pstmt.setString(4, m.getDescription());
            pstmt.setBoolean(5, m.isAvailable());
            pstmt.setInt(6, m.getMenuid());
            status = pstmt.executeUpdate(); // executeUpdate for update operation
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteMenu(String itemname) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(DELETE_ON_ID);
            pstmt.setString(1, itemname);
            status = pstmt.executeUpdate(); // executeUpdate for delete operation
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
  
    @Override
    public ArrayList<Menu> getAllMenusOnResID(int restaurantId) {
        ArrayList<Menu> menuList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(SELECT_ON_RESID);
            pstmt.setInt(1, restaurantId); // Set the restaurant ID in the query
            resultSet = pstmt.executeQuery();
            menuList = extractMenuFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }


    private ArrayList<Menu> extractMenuFromResultSet(ResultSet resultSet) {
        ArrayList<Menu> menuList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                 menu = new Menu(
                    resultSet.getInt("menuid"),
                    resultSet.getInt("restaurantid"),
                    resultSet.getString("itemName"),
                    resultSet.getDouble("price"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("isAvailable"),
                    resultSet.getString("imgpath")
                );
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

	
}
