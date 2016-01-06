package br.com.fatec.socialnet.web.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService service;

	@Override
	public void init() throws ServletException {
		this.service = ImplementationFinder.getImpl(UserService.class);	
	}
	@Override
	public void destroy() {
		this.service = null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("friendId");
		String action =  req.getParameter("type");
		User friend = new User();

		if (id != null) {
			friend = this.service.findById(Long.parseLong(id));
			
			if(action!=null && action.equals("friend")){
	    		List<Post> friends_posts = friend.getPosts();
	    		req.setAttribute("friends_posts", friends_posts);
	    	}
		}	
		
		req.setAttribute("friend",friend);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
	}

}
