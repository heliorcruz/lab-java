package br.com.fatec.socialnet.api.entity;

import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class Like extends SocialNetIdentifier {

	private Long userId;
	private String type;
	private Long typeId;
	private User user;

	public static final String TABLE_NAME = "social_like";
	public static final String COL_USERID = "userId";
	public static final String COL_TYPE = "type";
	public static final String COL_TYPEID = "typeId";

	public Like() {
	}

	public Like(Long id, Long userId, String type, Long typeId) {
		super(id);
		this.userId = userId;
		this.type = type;
		this.typeId = typeId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public User getUser() {
		if (this.user == null) {
			UserDAO dao = (UserDAO) ImplementationFinder.getImpl(UserDAO.class);
			this.user = dao.findByID(getUserId());
		}
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean equals(Object o) {
		Like l = (Like) o;
		return this.getUserId() == l.getUserId()
				&& this.getTypeId() == l.getTypeId()
				&& this.getType().equals(l.getType());
	}
}
