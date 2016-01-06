package br.com.fatec.socialnet.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.fatec.socialnet.api.dao.CommentDAO;
import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TestComment extends SocialNetBaseTest {

	private CommentDAO dao;
	private PostDAO pDao;
	private UserDAO uDao;

	// Metodo que retorna a classe que implemanta o DAO
	private void GetDAOImp() {
		dao = (CommentDAO) ImplementationFinder.getImpl(CommentDAO.class);
		pDao = (PostDAO) ImplementationFinder.getImpl(PostDAO.class);
		uDao = (UserDAO) ImplementationFinder.getImpl(UserDAO.class);
	}
	
	public Comment getCommentTest(){
		
		startBD();
		User uTest = uDao.findByEmail("helio@hotmail.com");
		Post pTest = pDao.save(new Post(null, "topico teste 1", "abcdefghyjklmnopqrs",java.sql.Date.valueOf("2015-10-10"),uTest.getId()));

		Comment novo = new Comment(null, "comment 1",
				java.sql.Date.valueOf("2015-10-10"), uTest.getId(),
				pTest.getId());
		
		return dao.save(novo);
		
	}

	@Test
	public void saveTest() {

		GetDAOImp();		
		Comment novo = getCommentTest();	
		assertEquals(novo,dao.save(novo));

	}

	@Test
	public void findallTest() {

		GetDAOImp();	
		List<Comment> lista = dao.findAll();
		assertEquals(lista.size(), 3);

	}

	@Test
	public void updateTest() {

		GetDAOImp();
		Comment modificado = dao.findByID(getCommentTest().getId());
		modificado.setMessage("comment 3");
		assertEquals(modificado, dao.update(modificado));

	}

	@Test
	public void deleteTest() {

		GetDAOImp();
		Comment removido = dao.findByID(getCommentTest().getId());
		dao.delete(removido);
		assertEquals(null, dao.findByID(removido.getId()));

	}

}
