package br.com.fatec.socialnet.web.servlet.comment;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.socialnet.api.entity.Comment;

import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.CommentService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class FormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CommentService service;

	@Override
	public void init() throws ServletException {
		this.service = ImplementationFinder.getImpl(CommentService.class);
	}

	@Override
	public void destroy() {
		this.service = null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = (User) req.getAttribute("user");
		String postIdComment = req.getParameter("postIdComment");
		String newComment = req.getParameter("new_comment");
		String idDelete = req.getParameter("commIdDelete");
		String comment=null;
		
		if(newComment !=null)comment = newComment.trim();
	
		if (postIdComment != null) {			
			if(comment!=null && !comment.isEmpty()){
				service.save(new Comment(null, comment, new java.sql.Date(Calendar.getInstance().getTime().getTime()), user.getId(), Long.parseLong(postIdComment)));
			}	
		}
		
		if(idDelete!=null && !idDelete.isEmpty()){
			Comment comm = service.findByID(Long.parseLong(idDelete));
			service.delete(comm);
		}

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index");
		rd.forward(req, resp);
	}

}
