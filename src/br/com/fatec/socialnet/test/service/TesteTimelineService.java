package br.com.fatec.socialnet.test.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.fatec.socialnet.core.TimelineService;
import br.com.fatec.socialnet.test.SocialNetBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TesteTimelineService extends SocialNetBaseTest{
	
	private UserService uService;
	
	//Metodo que retorna a classe que implemanta o DAO
	TimelineService service;
	private void getService() {
		service =  ImplementationFinder.getImpl(TimelineService.class);
		uService = ImplementationFinder.getImpl(UserService.class);	
		startBD();
	}
	
	@Test	
	public void findPostsByUserTest(){
		
		getService();	
		User uTest = uService.findByEmail("helio@hotmail.com");				
		List<Post> posts = service.getPostsByUser(uTest.getId());		
		assertEquals(posts.size(),1);	
		
	}
	
	@Test	
	public void findCommentsByPostTest(){
		
		getService();
		User uTest = uService.findByEmail("helio@hotmail.com");	
		List<Post> posts = service.getPostsByUser(uTest.getId());
		
		for (Post p: posts){
			List<Comment> comms = service.getCommentsByPost(p.getId());		
			assertEquals(comms.size(),1);
		}		
	}
	
	@Test	
	public void findLikesByPostTest(){
		
		getService();
		User uTest = uService.findByEmail("helio@hotmail.com");	
		List<Post> posts = service.getPostsByUser(uTest.getId());
		System.out.println(uTest.getId());
		
		for (Post p: posts){
			List<Like> likes = service.getLikesByPost(p.getId());			
			assertEquals(likes.size(),1);
		}		
	}

}
