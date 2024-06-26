package com.ecom.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.controller.dao.CategoryDao;
import com.ecom.model.ProductCategoryModel;
import com.ecom.utils.StringUtils;
/**
 * Servlet implementation class AddCategory
 */
@WebServlet("/AddCategory")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CategoryDao categoryDao = new CategoryDao();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String categoryName = request.getParameter("categoryName");
		
		ProductCategoryModel productCategoryModel = new ProductCategoryModel(categoryName);
		
		int result = categoryDao.addCategory(productCategoryModel);
		
		if(result>0) {
//			HttpSession session = request.getSession();
//	        
//	        session.setAttribute("categoryName", CategoryName);
			
			 List<ProductCategoryModel> categories = categoryDao.getAllCategories();

		        // Set the categories as an attribute in the request scope
			 request.setAttribute("categories", categories);

		        // Redirect to the JSP with success message
		    //yo kaam xa hai
			 
			response.sendRedirect(request.getContextPath() + "/jsp/productCategory.jsp" + "?"
                    + StringUtils.ERROR_MESSAGE + "=" + StringUtils.SUCCESS_ADD_CATEGORY);

			
			// yo kaam xaina hai
//			 request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}else {
	        // Redirect to register page with error message
	        response.sendRedirect(request.getContextPath() + "/jsp/productCategory.jsp" + "?" + StringUtils.ERROR_MESSAGE
	                + "=" + StringUtils.ADD_CATEGORY_ERROR_MESSAGE);
	        System.out.println("An unexpected server error occurred. ");
	    }
	
		
		
	}

}
