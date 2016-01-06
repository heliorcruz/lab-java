package br.com.fatec.socialnet.api.entity;

import java.sql.Date;
import java.util.List;

import br.com.fatec.socialnet.api.dao.LikeDAO;
import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class Comment extends SocialNetIdentifier{	
	
	private String message;
	private Date date;
	private Long userId;
	private Long postId;
	private User user;
	private Post post;
	private List<Like> likes;
	private int numLikes;
	
	public static final String TABLE_NAME = "social_comment";
	public static final String COL_POSTID = "postid";
	public static final String COL_MESSAGE = "message";
	public static final String COL_DATE = "date";
	public static final String COL_USERID = "userId";	
	
	public Comment(){}
	
	public Comment(Long id,String message, Date date, Long userId, Long postId) {
		super(id);
		this.message = message;
		this.date = date;
		this.userId = userId;
		this.postId = postId;	
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public User getUser() {
		if (this.user == null){			
			UserDAO dao = (UserDAO) ImplementationFinder.getImpl(UserDAO.class);
			this.user = dao.findByID(getUserId());	
		}		
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean equals(Object o){		
		Comment c = (Comment)o;		
		return  this.getMessage().equals(c.getMessage()) && this.getDate().equals(c.getDate()) &&
				this.getUserId().equals(c.getUserId()) && this.getPostId().equals(c.getPostId());		
	}

	public Post getPost() {
		if (this.post == null){			
			PostDAO dao = (PostDAO) ImplementationFinder.getImpl(PostDAO.class);
			this.post = dao.findByID(getPostId());		
		}		
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Like> getLikes() {
		if (this.likes == null){			
			LikeDAO dao = ImplementationFinder.getImpl(LikeDAO.class);
			this.likes = dao.getLikesByComment(this.getId());	
		}		
		return this.likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public int getNumLikes() {	
		List<Like> likes = getLikes();
		this.numLikes = likes.size();
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}
	
	

}
