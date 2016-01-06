package br.com.fatec.socialnet.test.service;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.fatec.socialnet.test.SocialNetBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TesteUserService extends SocialNetBaseTest{
	
	private UserService uService;
	
	private void getService() {
		uService =  ImplementationFinder.getImpl(UserService.class);
		startBD();
	}
	
	@Test	
	public void removeUserCascadePostsAndLikesAndCommentsTest(){		
		getService();	
		User uTest = uService.findByEmail("helio@hotmail.com");				
		uService.delete(uTest);
		assertEquals(null,uService.findById(uTest.getId()));		
	}

}
