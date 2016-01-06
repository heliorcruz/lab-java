package br.com.fatec.socialnet.web.servlet.post;

import java.io.IOException;
import java.util.Calendar;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.PostService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class FormServlet extends HttpServlet {

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
		String newTopic = req.getParameter("new_topic");
		String newMessage = req.getParameter("new_message");
		String postIdDelete = req.getParameter("postIdDelete");
		String postIdUpdate = req.getParameter("postIdUpdate");
		
		String topic=null;
		String message=null;
		if(newTopic != null) {
			topic = newTopic.trim();
		}
		if(newMessage != null){
			message = newMessage.trim();
		}
	
		if (user != null) {
			if (topic != null && message != null && !topic.isEmpty()
					&& !newMessage.isEmpty()) {
				
				if (postIdUpdate!=null && !postIdUpdate.isEmpty()) {
					sPost.update(new Post(Long.parseLong(postIdUpdate), topic, message,new java.sql.Date(Calendar.getInstance().getTime()
									.getTime()), user.getId()));
				}else{
					sPost.save(new Post(null, topic, message,new java.sql.Date(Calendar.getInstance().getTime()
							.getTime()), user.getId()));
				}				
				
			}			
		}
		if(postIdDelete!=null && !postIdDelete.isEmpty()){
			Post post = sPost.findById(Long.parseLong(postIdDelete));
			sPost.delete(post);
		}

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index");
		rd.forward(req, resp);
	}

}
