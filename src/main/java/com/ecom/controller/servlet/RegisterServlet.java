package com.ecom.controller.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.ecom.controller.DatabaseController;
import com.ecom.model.AddressModel;
import com.ecom.model.UserModel;
import com.ecom.utils.StringUtils;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController db = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // TODO Auto-generated method stub
	    doGet(request, response);
	    String userName = request.getParameter(StringUtils.userName);
	    String firstName = request.getParameter(StringUtils.firstName);
	    String lastName = request.getParameter(StringUtils.lastName);
	    String email = request.getParameter(StringUtils.email);
	    String phoneNumber = request.getParameter(StringUtils.phoneNumber);
	    String password = request.getParameter(StringUtils.password);
	    String rePassword = request.getParameter(StringUtils.rePassword);
	    
	    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	    UserModel.setPassword(hashedPassword);
	    
	    
	    String dobString = request.getParameter(StringUtils.dob);
	    LocalDate dob = LocalDate.parse(dobString);
	    String gender = request.getParameter(StringUtils.gender);
	    String city = request.getParameter(StringUtils.city);
	    String province = request.getParameter(StringUtils.province);
	    String country = request.getParameter(StringUtils.country);
	    String postalCode = request.getParameter(StringUtils.postalCode);

	    System.out.println("Received user registration details:");
	    System.out.println("Username: " + userName);
	    System.out.println("First Name: " + firstName);
	    System.out.println("Last Name: " + lastName);
	    System.out.println("Email: " + email);
	    System.out.println("Phone Number: " + phoneNumber);
	    System.out.println("Password: " + hashedPassword);
	    System.out.println("Date of Birth: " + dob);
	    System.out.println("Gender: " + gender);
	    System.out.println("City: " + city);
	    System.out.println("Province: " + province);
	    System.out.println("Country: " + country);
	    System.out.println("Postal Code: " + postalCode);

	    UserModel userModel = new UserModel(userName, firstName, lastName, email, phoneNumber, hashedPassword, dob,
	            gender);

	    // Adding user to the database
	    int userId = db.addUser(userModel);
	    System.out.println("Generated UserId: " + userId); // Debugging statement

	    if (userId > 0) {
	        HttpSession session = request.getSession();
	        
	        session.setAttribute(StringUtils.firstName, firstName);
	        session.setAttribute(StringUtils.lastName, lastName);
	        session.setAttribute(StringUtils.phoneNumber, phoneNumber);
	        session.setAttribute(StringUtils.dob, dob);
	        session.setAttribute(StringUtils.email, email);
	        session.setAttribute(StringUtils.gender, gender);

	        // If user added successfully, create AddressModel instance
	        AddressModel addressModel = new AddressModel(userId, city, province, country, postalCode); // Modified this line
	        System.out.println("AddressModel UserId: " + addressModel.getUserId()); // Debugging statement

	        // Add address to the database
	        int result = db.addAddress(addressModel);

	        if (result > 0) {
	            session.setAttribute(StringUtils.city, city);
	            session.setAttribute(StringUtils.province, province);
	            session.setAttribute(StringUtils.country, country);
	            session.setAttribute(StringUtils.postalCode, postalCode);
	            
	            // Redirect to login page with success message
	            response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_PAGE + "?"
	                    + StringUtils.ERROR_MESSAGE + "=" + StringUtils.SUCCESS_REGISTER_MESSAGE);
	        } else {
	            // Redirect to register page with error message
	            response.sendRedirect(request.getContextPath() + StringUtils.REGISTER_PAGE + "?"
	                    + StringUtils.ERROR_MESSAGE + "=" + StringUtils.ADDRESS_ERROR_MESSAGE);
	        }
	    } else {
	        // Redirect to register page with error message
	        response.sendRedirect(request.getContextPath() + StringUtils.REGISTER_PAGE + "?" + StringUtils.ERROR_MESSAGE
	                + "=" + StringUtils.REGISTER_ERROR_MESSAGE);
	        System.out.println("An unexpected server error occurred. ");
	    }
	}


}