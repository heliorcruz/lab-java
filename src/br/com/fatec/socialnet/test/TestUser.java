package br.com.fatec.socialnet.test;


import static org.junit.Assert.*;

import java.util.List;




import org.junit.Test;

import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TestUser extends SocialNetBaseTest {	
	
	private UserDAO dao;	

	//Metodo que retorna a classe que implemanta o DAO
	private  void GetDAOImp(){		
		dao = ImplementationFinder.getImpl(UserDAO.class);		
	}
	
	public User getUserTeste(){	
		startBD();
		return dao.findByEmail("helio@hotmail.com");
	}
		
	@Test	
	public void saveTest(){			
		GetDAOImp();
		
		User novo = new User(null,"Helio","Ribeiro da Cruz",java.sql.Date.valueOf("1985-10-16"),"helio@hotmail.com","1234");
	    //Teste que compara objeto criado com o inserido no banco		
		assertEquals(novo,dao.save(novo));
	}
	
	@Test	
	public void findallTest(){		
		
		GetDAOImp();
		List<User> lista = dao.findAll();		
		//Teste que verifica a quantidade de usuarios inseridos no banco
		assertEquals(lista.size(),1);			
	}
		
	@Test	
	public void updateTest(){		
		
		GetDAOImp();		
		User modificado = getUserTeste();			
		modificado.setName("Marcos R ");
		//Teste que verifica o update de um usuario
		assertEquals(modificado,dao.update(modificado));
		
	} 
	
	@Test	
	public void deleteTest(){		
		
		GetDAOImp();			
		User removido = getUserTeste();				
	    dao.delete(removido);	    
	    //Teste que verifica a exclusao de um usuario do banco
	    assertEquals(null,dao.findByID(removido.getId()));	  
		
	} 
		
}