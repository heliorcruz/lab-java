package br.com.fatec.socialnet.web.servlet.like;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.CommentService;
import br.com.fatec.socialnet.api.service.LikeService;
import br.com.fatec.socialnet.api.service.PostService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class FormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LikeService service;
	private PostService pService;
	

	@Override
	 public void init() throws ServletException {
		this.service = ImplementationFinder.getImpl(LikeService.class);	
		this.pService = ImplementationFinder.getImpl(PostService.class);
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = (User) req.getAttribute("user");
		String idNewLikePost = req.getParameter("idNewLikePost"); 
		String idNewLikeComment = req.getParameter("idNewLikeComment");		    
	
		if (idNewLikePost != null) {		
			service.save(new Like(null, user.getId(), Post.TABLE_NAME, Long.parseLong(idNewLikePost)));			
		}
		
		if (idNewLikeComment != null) {		
			service.save(new Like(null, user.getId(), Comment.TABLE_NAME, Long.parseLong(idNewLikeComment)));		
		}		

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				"/index");
		rd.forward(req, resp);
	}


}
