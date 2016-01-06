package br.com.fatec.socialnet.web.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.fatec.socialnet.api.dao.CommentDAO;
import br.com.fatec.socialnet.api.dao.LikeDAO;
import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.api.service.UserService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;



public class ApplicationConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		UserDAO uDao = ImplementationFinder.getImpl(UserDAO.class);         

	
		User novo= uDao.save(new User(null,"Helio","Ribeiro da Cruz",java.sql.Date.valueOf("1985-10-16"),"helio@hotmail.com","1234"));
		
		
		PostDAO pDao = ImplementationFinder.getImpl(PostDAO.class);

		LikeDAO dao = (LikeDAO) ImplementationFinder.getImpl(LikeDAO.class);
		CommentDAO cDao = (CommentDAO) ImplementationFinder.getImpl(CommentDAO.class);
	
		
		Post newPost = pDao.save(new Post(null, "topico teste 1", "hushduhasuhdusahduhsaud",java.sql.Date.valueOf("2015-10-10"),1l));
		Post newPost2 = pDao.save(new Post(null, "topico teste 2", "hushduhasuhdusahduhsaud",java.sql.Date.valueOf("2015-10-11"),1l));
		pDao.save(new Post(null, "topico teste 3", "hushduhasuhdusahduhsaud",java.sql.Date.valueOf("2015-10-12"),1l));
		
		cDao.save(new Comment(null, "comment 1 post 1",java.sql.Date.valueOf("2015-10-10"),novo.getId(),newPost.getId()));
		cDao.save(new Comment(null, "comment 2 post 1",java.sql.Date.valueOf("2015-10-10"),novo.getId(),newPost.getId()));
		cDao.save(new Comment(null, "comment 3 post 1",java.sql.Date.valueOf("2015-10-10"),novo.getId(),newPost.getId()));
	    
		cDao.save(new Comment(null, "comment 1 post 2",java.sql.Date.valueOf("2015-10-10"),novo.getId(),newPost2.getId()));
		cDao.save(new Comment(null, "comment 2 post 2",java.sql.Date.valueOf("2015-10-10"),novo.getId(),newPost2.getId()));
		cDao.save(new Comment(null, "comment 3 post 2",java.sql.Date.valueOf("2015-10-10"),novo.getId(),newPost2.getId()));
	    
	 	dao.save(new Like(null,novo.getId(),Post.TABLE_NAME,newPost.getId())); 
	 	dao.save(new Like(null,novo.getId(),Post.TABLE_NAME,newPost.getId())); 
	 	
	 	User nUser2 = uDao.save(new User(null,"Marcos","Ribeiro",java.sql.Date.valueOf("1985-10-03"),"mm@hotmail.com","1234"));
		User nUser3 = uDao.save(new User(null,"Camila","Ribeiro",java.sql.Date.valueOf("1985-10-03"),"cc@hotmail.com","1234"));
		ImplementationFinder.getImpl(UserService.class).addFriend(novo, nUser2); 
		ImplementationFinder.getImpl(UserService.class).addFriend(novo, nUser3); 
		
		pDao.save(new Post(null, "topico teste 4", "wwwwwwwwwwwwwww",java.sql.Date.valueOf("2015-10-12"),nUser2.getId()));
		pDao.save(new Post(null, "topico teste 5", "wwwwwwwwwwwwwww",java.sql.Date.valueOf("2015-10-12"),nUser2.getId()));
		
	}

}
