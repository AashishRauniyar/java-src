package com.ecom.utils;

public class QueryUtils {

	public static final String insertUserQuery = "INSERT INTO Users (userName, firstName, lastName, email, phoneNumber,password, dob, gender) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
	public static final String insertAddressQuery = "INSERT INTO Address (userId, city, province, country, postalCode) VALUES (?, ?, ?, ?, ?)";
	
	
	//query to get product
	public static final String insertProductQuery = "INSERT INTO product ( productName, productBrand, price, productDescription, imageUrl, productCategoryId, stock)"
			+ "VALUES" + "(?,?,?,?,?,?,?)";
	public static final String insertCategoryQuery = "INSERT INTO productCategory (productCategoryId, categoryName)"
			+ "VALUES" + "(?,?)";
	
	
	// to fetch admin data
	
	
	public static final String GET_LOGIN_USER_INFOS = "SELECT userName, password FROM Users WHERE userName = ? And password = ?";


	public static final String GET_USER = "SELECT * from Users";

	public static final String GET_HASHED_PASSWORD = "SELECT password FROM Users WHERE userName = ?";

	public static final String GET_DETAILS_BYUSERNAME = "SELECT * FROM Users WHERE userName = ?";
	
	public static final String GET_LOGIN_ADMIN_INFOS = "SELECT password FROM Users WHERE userName = ?";
	
	
	// profile update 
	public static final String UPDATE_USER_DETAILS = "UPDATE Users SET firstName = ?, lastName = ?, email = ?, "
	        + "phoneNumber = ?, gender = ? WHERE userName = ?";

	public static final String UPDATE_ADDRESS_DETAILS = "UPDATE Address SET city = ?, province = ?, "
	        + "country = ?, postalCode = ? WHERE userId = ?";

    
}
