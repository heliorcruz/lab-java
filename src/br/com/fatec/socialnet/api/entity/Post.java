package br.com.fatec.socialnet.api.entity;

import java.sql.Date;
import java.util.List;

import br.com.fatec.socialnet.api.dao.CommentDAO;
import br.com.fatec.socialnet.api.dao.LikeDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class Post extends SocialNetIdentifier{
	
	private String topic;
	private String message;
	private Date date;
	private Long userId;
	private User user;
	private List<Comment> comments;	
	private List<Like> likes;
	private int numComments;
	private int numLikes;

	public static final String TABLE_NAME = "social_post";
	public static final String COL_TOPIC = "topic";
	public static final String COL_MESSAGE = "message";
	public static final String COL_DATE = "date";
	public static final String COL_USERID = "userId";
	
	public Post(){};	
	
	public Post(Long id,String topic, String message, Date date, Long userId) {
		super(id);
		this.topic = topic;
		this.message = message;
		this.date = date;
		this.userId = userId;		
	}

    public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {		
		
		if (this.user == null){
			
			UserDAO dao = (UserDAO) ImplementationFinder.getImpl(UserDAO.class);
			this.user = dao.findByID(getUserId());			
	
		}		
		return this.user;
	}	
	
	public boolean equals(Object o){
		
		Post p = (Post) o;
		
		return this.getTopic().equals(p.getTopic()) && this.getMessage().equals(p.getMessage()) && this.getDate().equals(p.getDate());
	}
	
	public List<Comment> getComments() {
		if (this.comments == null){			
			CommentDAO dao = (CommentDAO) ImplementationFinder.getImpl(CommentDAO.class);
			this.comments = dao.getCommentsByPost(this.getId());			
	
		}		
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Like> getLikes() {
		if (this.likes == null){			
			LikeDAO dao = (LikeDAO) ImplementationFinder.getImpl(LikeDAO.class);
			this.likes = dao.getLikesByPost(this.getId());	
		}		
		return this.likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public int getNumComments() {
		List<Comment> comms = getComments();
		this.numComments = comms.size();
		return numComments;
	}

	public void setNumComments(int numComments) {
		this.numComments = numComments;
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
