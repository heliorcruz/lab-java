package br.com.fatec.socialnet.core;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;

public interface FeedService {
	
	public List<Post> getPostsByFriends(User u);	
	public User addFriend(User user, User friend);
	public User findFriendbyId(User user, User friend);
	public List<User> findFriends(Long id);

}
