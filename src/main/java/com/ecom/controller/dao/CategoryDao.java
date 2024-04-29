package com.ecom.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecom.controller.DatabaseController;
import com.ecom.model.ProductCategoryModel;
import com.ecom.utils.QueryUtils;

public class CategoryDao {

	public int addCategory(ProductCategoryModel productCategoryModel) {
		try (Connection conn = DatabaseController.getConn()) {
            // Update data in the users table
            String updateUserQuery = QueryUtils.ADD_CATEGORY;
            PreparedStatement categoryStatement = conn.prepareStatement(updateUserQuery);

            
            categoryStatement.setString(1, productCategoryModel.getCategoryName());
            
            int categoryInserted = categoryStatement.executeUpdate();
            categoryStatement.close();

            return categoryInserted; // Return the result of address insertion

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1; // or handle the exception as needed
        }
    }
	
	public List<ProductCategoryModel> getAllCategories() {
	    List<ProductCategoryModel> categories = new ArrayList<>();
	    try (Connection conn = DatabaseController.getConn()) {
	        String getAllCategoriesQuery = QueryUtils.GET_ALL_CATEGORIES;
	        PreparedStatement statement = conn.prepareStatement(getAllCategoriesQuery);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            ProductCategoryModel category = new ProductCategoryModel();
	            
	            category.setCategoryName(resultSet.getString("categoryName"));
	            categories.add(category);
	        }
	        
	        // Debugging: Print retrieved categories
	        System.out.println("Retrieved categories: " + categories);

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	    }
	    return categories;
	}


           
}
