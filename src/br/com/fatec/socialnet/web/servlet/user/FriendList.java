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

import br.com.fatec.socialnet.api.service.PostService;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class FriendList extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserService service;
	private PostService pService;
	

	@Override
	 public void init() throws ServletException {
		this.service = ImplementationFinder.getImpl(UserService.class);	
		this.pService = ImplementationFinder.getImpl(PostService.class);
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getAttribute("user");			
		
		 if (user != null){
			    List<User> friends = service.findFriends(user);
			    req.setAttribute("friends",friends);		 			    	
		    	
		    	List<Post> friends_posts = service.findPostsByFriends(user);
				req.setAttribute("friends_posts", friends_posts);
		 }
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
	}

}
