package br.com.fatec.socialnet.api.service;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;

public interface UserService {
	
	public User save(User user);

	public User update(User user);

	public void delete(User user);

	public List<User> findAll();

	public User findById(Long id);

	public User findByEmail(String email);

	public User findByEmailAndPassword(String email, String password);
	
	void removePostsByUserCascadeCommentsAndLikes(Long id);
	
	List<User> findFriends(User u);	
	User addFriend(User u, User friend);
	User findFrienById(Long userId, Long friendId);
	List<Post> findPostsByFriends(User u);

}
