package br.com.fatec.socialnet.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import br.com.fatec.socialnet.api.dao.CommentDAO;
import br.com.fatec.socialnet.api.dao.LikeDAO;
import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;

public class SocialNetBaseTest {

	@BeforeClass
	public static void setup() {

		ContextSpecifier.setContext("br.com.fatec.socialnet");

		ConfigDBMapper.setDefaultConnectionName("test");

		LiquibaseRunnerService.run();

	}

	@AfterClass
	public static void setDown() throws SQLException {

		Connection defaultConnection = ConfigDBMapper.getDefaultConnection();
		defaultConnection.prepareStatement("DELETE FROM social_like").execute();
		defaultConnection.prepareStatement("DELETE FROM social_comment")
				.execute();
		defaultConnection.prepareStatement("DELETE FROM social_post").execute();
		defaultConnection.prepareStatement("DELETE FROM social_user").execute();
		DbUtils.closeQuietly(defaultConnection); 

	}

	public static void startBD(){
		
		LikeDAO dao = (LikeDAO) ImplementationFinder.getImpl(LikeDAO.class);
		CommentDAO cDao = (CommentDAO) ImplementationFinder.getImpl(CommentDAO.class);
		PostDAO pDao = (PostDAO) ImplementationFinder.getImpl(PostDAO.class);
		UserDAO uDao = (UserDAO) ImplementationFinder.getImpl(UserDAO.class);		
		
		User nUser = uDao.save(new User(null,"User Teste","Ribeiro da Cruz",java.sql.Date.valueOf("1985-10-16"),"helio@hotmail.com","1234"));
		Post nPost = pDao.save(new Post(null, "topico teste 1", "abcdefghyjklmnopqrs",java.sql.Date.valueOf("2015-10-10"),nUser.getId()));
	    cDao.save(new Comment(null, "comment 1",java.sql.Date.valueOf("2015-10-10"),nUser.getId(),nPost.getId()));
	    dao.save(new Like(null,nUser.getId(),Post.TABLE_NAME,nPost.getId())); 
	    
	    uDao.save(new User(null,"Ronan","Carmo da Cruz",java.sql.Date.valueOf("1985-10-10"),"ronan@hotmail.com","1234"));
	    User nUser2 = uDao.save(new User(null,"Marcos","Ribeiro",java.sql.Date.valueOf("1985-10-03"),"mm@hotmail.com","1234"));
		User nUser3 = uDao.save(new User(null,"Camila","Ribeiro",java.sql.Date.valueOf("1985-10-03"),"cc@hotmail.com","1234"));
		
		pDao.save(new Post(null, "topico teste 2", "aaaaaaaaaaaaaaa",java.sql.Date.valueOf("2015-10-10"),nUser2.getId()));
		pDao.save(new Post(null, "topico teste 3", "xxxxxxxxxxxxxxx",java.sql.Date.valueOf("2015-10-10"),nUser3.getId()));
	    
		
	}
}
