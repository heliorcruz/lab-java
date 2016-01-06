package br.com.fatec.socialnet.web.servlet.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;

import br.com.fatec.socialnet.core.TimelineService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TimelineService service;
	

	@Override
	 public void init() throws ServletException {
		this.service = ImplementationFinder.getImpl(TimelineService.class);		
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getAttribute("user");
		List<Post> posts = service.getPostsByUser(user.getId());
		req.setAttribute("user", user);
		req.setAttribute("post_list",posts);	
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
	}
}
