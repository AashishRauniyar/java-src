package com.ecom.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ecom.controller.DatabaseController;
import com.ecom.model.ProductModel;
import com.ecom.utils.QueryUtils;

public class ProductDao {

	
	
	public int addProduct(ProductModel product) {
		try(Connection conn = DatabaseController.getConn()){
			String insertQuery = QueryUtils.insertProductQuery;
			
			PreparedStatement ps = conn.prepareStatement(insertQuery);

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductBrand());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getProductDescription());
            ps.setString(5, product.getImageUrl());
            ps.setInt(6, product.getCategory().getProductCategoryId()); // Set category ID
            ps.setInt(7, product.getStock());
            
            return ps.executeUpdate();
		}catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return -1;
        }
		
	}
	
}
