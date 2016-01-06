package br.com.fatec.socialnet.core.service;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.fatec.socialnet.core.FeedService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class FeedServiceImp implements FeedService {
	
	private UserService uService;
			
	public FeedServiceImp(){
		this.uService = ImplementationFinder.getImpl(UserService.class);
	}

	@Override
	public List<Post> getPostsByFriends(User u) {
		return this.uService.findPostsByFriends(u);
	}

	@Override
	public User addFriend(User user, User friend) {
		User novo = uService.findFrienById(user.getId(), friend.getId());
		if(novo == null){
			return uService.addFriend(user, friend);
		}
		return null;		
	}

	@Override
	public List<User> findFriends(Long id) {
		User user =  uService.findById(id);
		if (user != null){
			return uService.findFriends(user);
		}
		return null;
	}

	@Override
	public User findFriendbyId(User user, User friend) {
		return uService.findFrienById(user.getId(), friend.getId());
	}

}
