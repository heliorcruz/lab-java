package br.com.fatec.socialnet.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class FiltroLogin implements Filter {

@Override
public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException,ServletException {

	
	String email = (String) req.getAttribute("user_email");
	
	HttpServletResponse httpResponse = (HttpServletResponse) resp;

	if (email != null) {
		User user = ImplementationFinder.getImpl(UserDAO.class).findByEmail(email);		
		req.setAttribute("user", user);
		chain.doFilter(req, resp);
	} else {
		httpResponse.sendRedirect("login.html");
	}
	return;
}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
