package br.com.fatec.socialnet.api.dao;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;

public interface UserDAO {

	User save(User u); // CREATE

	List<User> findAll(); // READ

	User findByID(Long id); // READ

	User update(User u); // UPDATE

	void delete(User u); // DELETE

	User findByEmail(String email);

	User findByLogin(String email, String password);

	List<User> findFriends(User u);	
	User addFriend(User u, User friend);
	User findFrienById(Long userId, Long friendId);
	List<Post> getPostsByFriends(User u);

}
