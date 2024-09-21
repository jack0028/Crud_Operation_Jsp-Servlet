package net.javaguides.usermanagement.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.usermanagement.dao.UserDAO;
import net.javaguides.usermanagement.model.User;

@WebServlet("/login")
public class LoginServle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public LoginServle() {
        this.userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String contact = request.getParameter("contact");
        int otp = Integer.parseInt(request.getParameter("otp")); 

        User loginUser = new User();
        loginUser.setContact(contact);
        loginUser.setOtp(otp);

        try {
            boolean isValidUser = userDAO.loginEmployee(loginUser);

            if (isValidUser) {
            	System.out.println("Logging in");
                RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
                dispatcher.forward(request, response);
            } else {
  
                request.setAttribute("errorMessage", "Invalid contact or OTP. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
