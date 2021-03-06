package br.com.fatec.socialnet.web.servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.User;

import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ListServlet extends HttpServlet {

	private UserDAO dao;

	@Override
	public void init() throws ServletException {
		this.dao = ImplementationFinder.getImpl(UserDAO.class);
	}

	@Override
	public void destroy() {
		this.dao = null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("user.id");
		User user = new User();

	/*	if (id != null) {
			user = this.dao.findByID(Long.parseLong(id));
			this.dao.delete(user);
			req.setAttribute("action", "removed");
			req.setAttribute("user", user);
		} */

		req.setAttribute("userList", this.dao.findAll());

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/user/list.jsp");
		rd.forward(req, resp);
	}
}
