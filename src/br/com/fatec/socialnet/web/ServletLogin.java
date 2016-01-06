package br.com.fatec.socialnet.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

@SuppressWarnings("serial")
public class ServletLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = ImplementationFinder.getImpl(UserDAO.class).findByLogin(email, password);		
		
		if (user != null) {
			Cookie ck = new Cookie("user_email", email);
			ck.setMaxAge(3000000);
			response.addCookie(ck);
			request.setAttribute("user", user);
			request.setAttribute("userId", user.getId());			
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index");			
			rd.forward(request, response);
		} else {
			response.sendRedirect("login.html");
		}
	}

}
