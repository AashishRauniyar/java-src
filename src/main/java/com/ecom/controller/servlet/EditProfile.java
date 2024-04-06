package com.ecom.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.controller.dao.ProfileDao;
import com.ecom.model.AddressModel;
import com.ecom.model.UserModel;
import com.ecom.utils.StringUtils;

@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProfile() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String country = request.getParameter("country");
        String postalCode = request.getParameter("postalCode");

        UserModel userModel = new UserModel(userName, firstName, lastName, email, phoneNumber, null, null, gender);

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
