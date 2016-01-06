package br.com.fatec.socialnet.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.fatec.socialnet.api.dao.CommentDAO;
import br.com.fatec.socialnet.api.dao.LikeDAO;
import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TesteLike extends SocialNetBaseTest {

	private LikeDAO dao;
	private CommentDAO cDao;
	private PostDAO pDao;
	private UserDAO uDao;

	// Metodo que retorna a classe que implemanta o DAO
	private void GetDAOImp() {
		dao = (LikeDAO) ImplementationFinder.getImpl(LikeDAO.class);
		cDao = (CommentDAO) ImplementationFinder.getImpl(CommentDAO.class);
		pDao = (PostDAO) ImplementationFinder.getImpl(PostDAO.class);
		uDao = (UserDAO) ImplementationFinder.getImpl(UserDAO.class);
		startBD();
	}

	@Test
	public void saveTest() {

		GetDAOImp();
		User uTest = uDao.findByEmail("helio@hotmail.com");
		Post pTest = pDao.save(new Post(null, "topico teste 1",
				"abcdefghyjklmnopqrs", java.sql.Date.valueOf("2015-10-10"),
				uTest.getId()));
		Comment cTest = cDao.save(new Comment(null, "comment 1",
				java.sql.Date.valueOf("2015-10-10"), uTest.getId(),
				pTest.getId()));
		
		Like novoPost = new Like(null,uTest.getId(),Post.TABLE_NAME,pTest.getId());
		Like novoComment = new Like(null,uTest.getId(),Comment.TABLE_NAME,cTest.getId());
				
		assertEquals(novoPost, dao.save(novoPost));
		assertEquals(novoComment, dao.save(novoComment));

	}

	@Test
	public void findallTest() {

		GetDAOImp();	
		List<Like> lista = dao.findAll();	
		assertEquals(lista.size(), 4);

	}

	@Test
	public void updateTest() {

		GetDAOImp();
		User uTest = uDao.findByEmail("helio@hotmail.com");
		Post pTest = pDao.save(new Post(null, "topico teste 1",
				"abcdefghyjklmnopqrs", java.sql.Date.valueOf("2015-10-10"),
				uTest.getId()));
		Like novoPost = new Like(null,uTest.getId(),Comment.TABLE_NAME,pTest.getId());
		Like modificado = dao.save(novoPost);
		modificado.setType(Post.TABLE_NAME);
		assertEquals(modificado, dao.update(modificado));

	}

	@Test
	public void deleteTest() {

		GetDAOImp();
		User uTest = uDao.findByEmail("helio@hotmail.com");
		Post pTest = pDao.save(new Post(null, "topico teste 1",
				"abcdefghyjklmnopqrs", java.sql.Date.valueOf("2015-10-10"),
				uTest.getId()));
		Like novoPost = new Like(null,uTest.getId(),Comment.TABLE_NAME,pTest.getId());		
		Like modificado = dao.save(novoPost);
		dao.delete(modificado);
		assertEquals(null, dao.findByID(modificado.getId()));

	}

}
