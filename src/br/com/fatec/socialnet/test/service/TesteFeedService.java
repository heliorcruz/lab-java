package br.com.fatec.socialnet.test.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.fatec.socialnet.core.FeedService;
import br.com.fatec.socialnet.test.SocialNetBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TesteFeedService extends SocialNetBaseTest {
	
private UserService uService;
	
	//Metodo que retorna a classe que implemanta o DAO
	FeedService service;
	private void getService() {
		service =  ImplementationFinder.getImpl(FeedService.class);
		uService = ImplementationFinder.getImpl(UserService.class);	
		startBD();
	}
	
	@Test	
	public void addFriendUserTest(){
		
		getService();	
		User uTest = uService.findByEmail("helio@hotmail.com");
		User nUser =  uService.findByEmail("ronan@hotmail.com");
		User friend = service.addFriend(uTest, nUser);
		 
		assertEquals(nUser,friend);	
		
	}
	
	@Test	
	public void findFriendsTest(){
		
		getService();
		User uTest = uService.findByEmail("helio@hotmail.com");	
		service.addFriend(uTest,  uService.findByEmail("mm@hotmail.com"));
		service.addFriend(uTest,  uService.findByEmail("cc@hotmail.com"));	
		
		List<User> friends = service.findFriends(uTest.getId());		
		
		assertEquals(friends.size(),2);
				
	}
	
	@Test	
	public void findPostbyFriends(){
		
		getService();
		User uTest = uService.findByEmail("helio@hotmail.com");	
		List<Post> posts = service.getPostsByFriends(uTest);		
		assertEquals(posts.size(),2);
				
	}

}
