package br.com.fatec.socialnet.web.servlet.post;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.PostService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class PostServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private PostService sPost;

	@Override
	public void init() throws ServletException {
		this.sPost = ImplementationFinder.getImpl(PostService.class);
	}

	@Override
	public void destroy() {
		this.sPost = null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		

		User user = (User) req.getAttribute("user");
		String idUpdate = req.getParameter("postIdUpdate");		
	
		if (idUpdate != null && !idUpdate.isEmpty()){
		  Post edit_post = sPost.findById(Long.parseLong(idUpdate));
		  req.setAttribute("edit_post", edit_post);
		}
			
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
	}

}
