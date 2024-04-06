package com.ecom.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.controller.DatabaseController;
import com.ecom.controller.dao.ProfileDao;
import com.ecom.controller.dao.UserProfileDetails;
import com.ecom.model.AddressModel;
import com.ecom.model.UserModel;
import com.ecom.utils.StringUtils;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String userName = (String) session.getAttribute("userName");
//
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String email = request.getParameter("email");
//        String phoneNumber = request.getParameter("phoneNumber");
//        String gender = request.getParameter("gender");
//        // Additional parameters for address
//        String city = request.getParameter("city");
//        String province = request.getParameter("province");
//        String country = request.getParameter("country");
//        String postalCode = request.getParameter("postalCode");
//
//        UserModel userModel = new UserModel(userName, firstName, lastName, email, phoneNumber, null, null, gender);
//        
//        userModel.setUserName(userName);
//        userModel.setFirstName(firstName);
//        userModel.setLastName(lastName);
//        userModel.setEmail(email);
//        userModel.setPhoneNumber(phoneNumber);
//        userModel.setGender(gender);
//
//        AddressModel addressModel = new AddressModel( city, province, country, postalCode);
//
//        ProfileDao profileDao = new ProfileDao();
//        boolean updated = profileDao.updateUserProfile(userModel, addressModel);
//
//        if (updated) {
//            // Update session attributes with new user details
//            session.setAttribute(StringUtils.firstName, firstName);
//            session.setAttribute(StringUtils.lastName, lastName);
//            session.setAttribute(StringUtils.email, email);
//            session.setAttribute(StringUtils.phoneNumber, phoneNumber);
//            session.setAttribute(StringUtils.gender, gender);
//            session.setAttribute(StringUtils.city, city);
//            session.setAttribute(StringUtils.province, province);
//            session.setAttribute(StringUtils.country, country);
//            session.setAttribute(StringUtils.postalCode, postalCode);
//
//            response.sendRedirect(request.getContextPath() + "/jsp/profile.jsp");
//        } else {
//            // Handle update failure
//            // Redirect back to profile page with an error message
//            response.sendRedirect(request.getContextPath() + "/jsp/profile.jsp?error=1");
//        }
//    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        // Additional parameters for address
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String country = request.getParameter("country");
        String postalCode = request.getParameter("postalCode");

        UserModel userModel = new UserModel(userName, firstName, lastName, email, phoneNumber, null, null, gender);

        userModel.setUserName(userName);
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setGender(gender);

        ProfileDao profileDao = new ProfileDao();
        int userId = profileDao.updateUserProfile(userModel); // This should return the auto-generated userId

        if (userId != -1) {
            AddressModel addressModel = new AddressModel(userId, city, province, country, postalCode);

            boolean updated = profileDao.updateAddress(addressModel);

            if (updated) {
                // Update session attributes with new user details
                session.setAttribute(StringUtils.firstName, firstName);
                session.setAttribute(StringUtils.lastName, lastName);
                session.setAttribute(StringUtils.email, email);
                session.setAttribute(StringUtils.phoneNumber, phoneNumber);
                session.setAttribute(StringUtils.gender, gender);
                session.setAttribute(StringUtils.city, city);
                session.setAttribute(StringUtils.province, province);
                session.setAttribute(StringUtils.country, country);
                session.setAttribute(StringUtils.postalCode, postalCode);

                response.sendRedirect(request.getContextPath() + "/jsp/profile.jsp");
            } else {
                // Handle address update failure
                // Redirect back to profile page with an error message
                response.sendRedirect(request.getContextPath() + "/jsp/profile.jsp?error=1");
            }
        } else {
            // Handle user update failure
            // Redirect back to profile page with an error message
            response.sendRedirect(request.getContextPath() + "/jsp/profile.jsp?error=1");
        }
    }

}
