package br.com.fatec.socialnet.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TestPost extends SocialNetBaseTest{
	
	private PostDAO dao;
	private UserDAO uDao;
	
	//Metodo que retorna a classe que implemanta o DAO
	private void GetDAOImp(){		
		dao = (PostDAO) ImplementationFinder.getImpl(PostDAO.class);	
		uDao = (UserDAO) ImplementationFinder.getImpl(UserDAO.class);	
	}
	
	private Post getPostTest(){
		startBD();
		User uTest = uDao.findByEmail("helio@hotmail.com");
		Post novo = new Post(null, "topico teste 1", "abcdefghyjklmnopqrs",java.sql.Date.valueOf("2015-10-10"),uTest.getId());
		return dao.save(novo);
	}
	
	@Test	
	public void saveTest(){		
		
		GetDAOImp();		
		Post novo = getPostTest();;
		assertEquals(novo,dao.save(novo));			
	}
	
	@Test	
	public void findallTest(){	
		
		GetDAOImp();
		List<Post> lista = dao.findAll();		
		assertEquals(lista.size(),5);			
		
	}
		
	@Test	
	public void updateTest(){	
		
		GetDAOImp();			
		Post modificado = dao.findByID(getPostTest().getId());			
		modificado.setTopic("topico teste 3");		
		assertEquals(modificado,dao.update(modificado));			
	} 
	
	@Test	
	public void deleteTest(){		
		
		GetDAOImp();		
		Post removido = dao.findByID(getPostTest().getId());					
	    dao.delete(removido);	       
	    assertEquals(null,dao.findByID(removido.getId()));	  
		
	} 
		

}
