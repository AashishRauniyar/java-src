package com.ecom.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ecom.model.AddressModel;
import com.ecom.model.UserModel;
import com.ecom.controller.DatabaseController;

public class ProfileDao {

//    public boolean updateUserProfile(UserModel userModel, AddressModel addressModel) {
//        try (Connection conn = DatabaseController.getConn()) {
//            // Update data in the users table
//            String updateUserQuery = "UPDATE users SET firstName=?, lastName=?, email=?, phoneNumber=?, gender=? WHERE userName=?";
//            PreparedStatement userStatement = conn.prepareStatement(updateUserQuery);
//
//            userStatement.setString(1, userModel.getFirstName());
//            userStatement.setString(2, userModel.getLastName());
//            userStatement.setString(3, userModel.getEmail());
//            userStatement.setString(4, userModel.getPhoneNumber());
//            userStatement.setString(5, userModel.getGender());
//            userStatement.setString(6, userModel.getUserName());
//
//            int userUpdated = userStatement.executeUpdate();
//            userStatement.close();
//
//            // Update data in the address table
//            String updateAddressQuery = "UPDATE address SET city=?, province=?, country=?, postalCode=? WHERE userId=?";
//            PreparedStatement addressStatement = conn.prepareStatement(updateAddressQuery);
//            addressStatement.setString(1, addressModel.getCity());
//            addressStatement.setString(2, addressModel.getProvince());
//            addressStatement.setString(3, addressModel.getCountry());
//            addressStatement.setString(4, addressModel.getPostalCode());
//            addressStatement.setInt(5, addressModel.getUserId());
//            int addressUpdated = addressStatement.executeUpdate();
//            addressStatement.close();
//
//            return userUpdated > 0 && addressUpdated > 0;
//        } catch (SQLException | ClassNotFoundException ex) {
//            ex.printStackTrace(); // Handle exception appropriately
//            return false;
//        }
//    }
	

	    public int updateUserProfile(UserModel userModel) {
	        try (Connection conn = DatabaseController.getConn()) {
	            // Update data in the users table
	            String updateUserQuery = "UPDATE users SET firstName=?, lastName=?, email=?, phoneNumber=?, gender=? WHERE userName=?";
	            PreparedStatement userStatement = conn.prepareStatement(updateUserQuery, Statement.RETURN_GENERATED_KEYS);

	            userStatement.setString(1, userModel.getFirstName());
	            userStatement.setString(2, userModel.getLastName());
	            userStatement.setString(3, userModel.getEmail());
	            userStatement.setString(4, userModel.getPhoneNumber());
	            userStatement.setString(5, userModel.getGender());
	            userStatement.setString(6, userModel.getUserName());

	            int userUpdated = userStatement.executeUpdate();
	            int userId = -1;
	            if (userUpdated > 0) {
	                ResultSet generatedKeys = userStatement.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    userId = generatedKeys.getInt(1);
	                }
	            }
	            ;
	            userStatement.close();
	            return userId;
	            
	        } catch (SQLException | ClassNotFoundException ex) {
	            ex.printStackTrace();
	            return -1;
	        }
	    }

	    public boolean updateAddress(AddressModel addressModel) {
	        try (Connection conn = DatabaseController.getConn()) {
	            // Update data in the address table
	            String updateAddressQuery = "UPDATE address SET city=?, province=?, country=?, postalCode=? WHERE userId=?";
	            PreparedStatement addressStatement = conn.prepareStatement(updateAddressQuery);

	            addressStatement.setString(1, addressModel.getCity());
	            addressStatement.setString(2, addressModel.getProvince());
	            addressStatement.setString(3, addressModel.getCountry());
	            addressStatement.setString(4, addressModel.getPostalCode());
	            addressStatement.setInt(5, addressModel.getUserId());

	            int addressUpdated = addressStatement.executeUpdate();
	            addressStatement.close();

	            return addressUpdated > 0;
	        } catch (SQLException | ClassNotFoundException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }
	}

