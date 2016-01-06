package br.com.fatec.socialnet.web.servlet.user;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class FormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		this.dao = ImplementationFinder.getImpl(UserDAO.class);
		this.userService = ImplementationFinder.getImpl(UserService.class);
	}

	@Override
	public void destroy() {
		this.dao = null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("user.id");
		User user = new User();
		user.setName(req.getParameter("user.name"));
		user.setEmail(req.getParameter("user.email"));
		user.setPassword(req.getParameter("user.password"));	

		if (!id.isEmpty()) {
			user.setId(Long.parseLong(id));
			user = this.userService.update(user);
			req.setAttribute("action", "updated");
		} else {
			user = this.userService.save(user);
			req.setAttribute("action", "created");
		}
		
		req.setAttribute("user", user);
		req.setAttribute("userList", this.dao.findAll());

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login");
		rd.forward(req, resp);
	}
	
	

}
