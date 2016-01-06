package br.com.fatec.socialnet.test.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.PostService;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.fatec.socialnet.test.SocialNetBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TestePostService extends SocialNetBaseTest{
	
private PostService pService;
private UserService uService;
	
	private void getService() {
		pService =  ImplementationFinder.getImpl(PostService.class);
		uService = ImplementationFinder.getImpl(UserService.class);
		startBD();
	}
	
	@Test	
	public void removePostsByUserCascadeLikesAndCommentsTest(){
		
		getService();		
		User uTest = uService.findByEmail("helio@hotmail.com");				
		List<Post> posts = pService.getPostsByUser(uTest.getId());
		
		assertEquals(1,1);
		
		for (Post p: posts){
			pService.delete(p);
		}
		
		posts.clear();
		
		List<Post> removed = pService.getPostsByUser(uTest.getId());
		assertEquals(posts,removed);		
	}

}
