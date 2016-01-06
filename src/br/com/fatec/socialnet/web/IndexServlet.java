package br.com.fatec.socialnet.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.CommentService;
import br.com.fatec.socialnet.api.service.PostService;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.fatec.socialnet.core.FeedService;
import br.com.fatec.socialnet.core.TimelineService;
import br.com.fatec.socialnet.core.service.TimelineServiceImp;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet{
	
	private UserDAO uDao;
	private FeedService feed;
	private TimelineService service;
	

	@Override
	public void init() throws ServletException {
		this.uDao = ImplementationFinder.getImpl(UserDAO.class);
		this.service = ImplementationFinder.getImpl(TimelineService.class);
		this.feed =  ImplementationFinder.getImpl(FeedService.class);	
	}

	@Override
	public void destroy() {
		this.uDao = null;
		this.service = null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getAttribute("user");
		String action =  (String) req.getParameter("type");		
		
		if (user != null) {
			user = this.uDao.findByID(user.getId());
		}
		
		if(action!=null && action.equals("user_posts")){
    		List<Post> posts = service.getPostsByUser(user.getId());
    		req.setAttribute("post_list", posts);
    	}
    		
    	if(action!=null && action.equals("friends_posts")){
    		List<Post> friends_posts = feed.getPostsByFriends(user);
    		req.setAttribute("friends_posts", friends_posts);
    	}
	    
		req.setAttribute("user", user);	
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
	}

	
}
