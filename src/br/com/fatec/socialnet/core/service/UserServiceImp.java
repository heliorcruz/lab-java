package br.com.fatec.socialnet.core.service;

import java.util.List;

import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.LikeService;
import br.com.fatec.socialnet.api.service.PostService;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class UserServiceImp implements UserService{
	
	private UserDAO dao;	

	public UserServiceImp() {
		this.dao = ImplementationFinder.getImpl(UserDAO.class);	
	}

	@Override
	public User save(User user) {
		User search = dao.findByEmail(user.getEmail());
		if (search == null)
			return this.dao.save(user);
		else
			return null;
	}

	@Override
	public User update(User user) {
		User newUser = this.dao.update(user);
		return this.dao.findByID(newUser.getId());
	}

	@Override
	public void delete(User user) {	
		removePostsByUserCascadeCommentsAndLikes(user.getId());
		this.dao.delete(user);
	}

	@Override
	public List<User> findAll() {
		return this.dao.findAll();
	}

	@Override
	public User findById(Long id) {
		return this.dao.findByID(id);
	}

	@Override
	public User findByEmail(String email) {
		return this.dao.findByEmail(email);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		return this.dao.findByLogin(email, password);
	}

	@Override
	public void removePostsByUserCascadeCommentsAndLikes(Long id) {
		PostService service = ImplementationFinder.getImpl(PostService.class);
		LikeService lService = ImplementationFinder.getImpl(LikeService.class);
		lService.removeLikesByUser(id);
		List<Post> posts = service.getPostsByUser(id);
		for(Post p: posts){
			service.delete(p);
		}		
	}

	@Override
	public List<User> findFriends(User u) {
		return dao.findFriends(u);
	}

	@Override
	public User addFriend(User u, User friend) {
		return dao.addFriend(u, friend);
	}

	@Override
	public User findFrienById(Long userId, Long friendId) {
		return dao.findFrienById(userId, friendId);
	}

	@Override
	public List<Post> findPostsByFriends(User u) {
		return dao.getPostsByFriends(u);
	}
	

}
