package br.com.fatec.socialnet.api.entity;

import java.sql.Date;
import java.util.List;



import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class User extends SocialNetIdentifier {

	private String name;
	private String lastname;
	private Date birthDate;
	private String email;;
	private String password;
	private List<User> friends;
	private List<Post> posts;
	private int numFriends;

	public final static String TABLE_NAME = "social_user";
	public final static String COL_NAME = "name";
	public final static String COL_LASTNAME = "lastname";
	public final static String COL_BIRTH_DATE = "birthDate";
	public final static String COL_EMAIL = "email";
	public final static String COL_PASSWORD = "user_password";

	public User() {
	};

	public User(Long id, String name, String lastname, Date birthDate,
			String email, String password) {
		super(id);
		this.name = name;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public boolean equals(Object obj) {

		User user = (User) obj;
		return this.getName().equals(user.getName())
				&& this.getLastName().equals(user.getLastName())
				&& this.getBirthDate().equals(user.getBirthDate())
				&& this.getEmail().equals(user.getEmail())
				&& this.getPassword().equals(user.getPassword());
	}

	public List<User> getFriends() {
		if (this.friends == null){			
			UserDAO dao = ImplementationFinder.getImpl(UserDAO.class);
			this.friends = dao.findFriends(this);		
		}		
		return this.friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<Post> getPosts() {
		this.posts = ImplementationFinder.getImpl(PostDAO.class).getPostsByUser(getId());
		return this.posts;	
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public int getNumFriends() {
		List<User> friends = getFriends();
		this.numFriends = friends.size();
		return numFriends;
	}

	public void setNumFriends(int numFriends) {
		this.numFriends = numFriends;
	}

}
