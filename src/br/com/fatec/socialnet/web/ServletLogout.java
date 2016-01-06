package br.com.fatec.socialnet.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ServletLogout extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	Cookie loginCookie = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("email_usuario")) {
				loginCookie = cookie;
				break;
			}
		}
	}
	if (loginCookie != null) {
		loginCookie.setMaxAge(0);
		response.addCookie(loginCookie);
	}
	response.sendRedirect("login.html");
}

}
