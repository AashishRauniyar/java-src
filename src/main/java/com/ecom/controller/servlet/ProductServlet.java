package com.ecom.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.controller.DatabaseController;
import com.ecom.model.ProductModel;
import com.ecom.utils.StringUtils;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    DatabaseController db = new DatabaseController();
    public ProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        List<ProductModel> productList = new ArrayList<>();

        
        
        try (Connection conn = DatabaseController.getConn()) {
            String query = "SELECT * FROM Product";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ProductModel product = new ProductModel(
                    resultSet.getInt("productId"), 
                    resultSet.getString("productName"), 
                    resultSet.getString("productBrand"), 
                    resultSet.getDouble("price"), 
                    resultSet.getString("productDescription"),
                    resultSet.getString("imageUrl"), 
                    resultSet.getInt("productCategoryId"), 
                    resultSet.getInt("stock")
                );

                productList.add(product);
                System.out.println(productList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Debugging: Print the productList size
        System.out.println("Product List Size: " + productList.size());

        request.setAttribute("productList", productList);
        request.getRequestDispatcher(StringUtils.WELCOME_PAGE).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
