package br.com.fatec.socialnet.web.servlet.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.CommentService;
import br.com.fatec.socialnet.api.service.PostService;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CommentService service;
	private PostService pService;
	

	@Override
	 public void init() throws ServletException {
		this.service = ImplementationFinder.getImpl(CommentService.class);	
		this.pService = ImplementationFinder.getImpl(PostService.class);
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getAttribute("user");		
		String postIdComment = req.getParameter("postIdComment");		
		
		 if (postIdComment != null){
			 	Post post = pService.findById(Long.parseLong(postIdComment));
		    	req.setAttribute("post",post);		    	
		    	List<Comment> comms = service.getCommentsByPost(Long.parseLong(postIdComment));		    
		    	req.setAttribute("comment_list",comms);				
		 }
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index");
		rd.forward(req, resp);
	}

}
