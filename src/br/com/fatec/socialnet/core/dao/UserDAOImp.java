package br.com.fatec.socialnet.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.dao.UserDAO;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.entity.User;
import br.com.fatec.socialnet.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class UserDAOImp implements UserDAO {

	Connection conn;
	PreparedStatement sql;

	public PreparedStatement openConnSQL(String sqlString) throws SQLException {
		this.conn = ConfigDBMapper.getDefaultConnection();
		return conn.prepareStatement(sqlString);
	}

	@Override
	public User save(User u) {

		try {

			sql = openConnSQL("INSERT INTO " + User.TABLE_NAME
					+ " VALUES(?,?,?,?,?,?)");

			Long id = GeradorIdService.getInstance().getNextId(User.TABLE_NAME);

			// set arguments
			sql.setLong(1, id);
			sql.setString(2, u.getName());
			sql.setString(3, u.getLastName());
			sql.setDate(4, u.getBirthDate());
			sql.setString(5, u.getEmail());
			sql.setString(6, u.getPassword());
			sql.execute();

			return this.findByID(id);

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	@Override
	public List<User> findAll() {

		try {

			return criarLista(openConnSQL("SELECT * FROM " + User.TABLE_NAME)
					.executeQuery());

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	public List<User> criarLista(ResultSet rs) throws SQLException {

		List<User> lista = new LinkedList<User>();

		while (rs.next()) {

			lista.add(createUser(rs));
		}
		return lista;

	}

	public User createUser(ResultSet rs) throws SQLException {

		return new User(rs.getLong(User.COL_ID), rs.getString(User.COL_NAME),
				rs.getString(User.COL_LASTNAME),
				rs.getDate(User.COL_BIRTH_DATE), rs.getString(User.COL_EMAIL),
				rs.getString(User.COL_PASSWORD));
	}

	@Override
	public User findByID(Long id) {

		try {

			sql = openConnSQL("SELECT * FROM " + User.TABLE_NAME
					+ " WHERE id = ?");
			sql.setLong(1, id);
			ResultSet rs = sql.executeQuery();
			if (rs.next())
				return createUser(rs);
			else
				return null;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	@Override
	public User update(User u) {

		try {

			sql = openConnSQL(" UPDATE " + User.TABLE_NAME + " " + " SET "
					+ User.COL_NAME + "=?," + User.COL_LASTNAME + "=?,"
					+ User.COL_BIRTH_DATE + "=?," +

					User.COL_EMAIL + "=?," + User.COL_PASSWORD + "= ?"
					+ " WHERE " + User.COL_ID + "=?");

			// set arguments
			sql.setString(1, u.getName());
			sql.setString(2, u.getLastName());
			sql.setDate(3, u.getBirthDate());
			sql.setString(4, u.getEmail());
			sql.setString(5, u.getPassword());
			sql.setLong(6, u.getId());
			sql.execute();

			return this.findByID(u.getId());

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void delete(User u) {

		try {
			sql = openConnSQL(" DELETE FROM " + User.TABLE_NAME + " "
					+ " WHERE " + User.COL_ID + "=?");

			sql.setLong(1, u.getId());
			;
			sql.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public User findByEmail(String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		User user = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			stmt = conn.prepareStatement("SELECT * FROM " + User.TABLE_NAME
					+ " WHERE " + User.COL_EMAIL + " = ?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = this.createUser(rs);
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}

	@Override
	public User findByLogin(String email, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		User user = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			stmt = conn.prepareStatement("SELECT * FROM " + User.TABLE_NAME
					+ " WHERE " + User.COL_EMAIL + " = ? and "
					+ User.COL_PASSWORD + " = ?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = this.createUser(rs);
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}

	@Override
	public User addFriend(User u, User f) {
		try {

			sql = openConnSQL("INSERT INTO social_friend VALUES(?,?,?)");

			Long id = GeradorIdService.getInstance().getNextId("social_friend");

			// set arguments
			sql.setLong(1, id);
			sql.setLong(2, u.getId());
			sql.setLong(3, f.getId());
			sql.execute();

			return this.findByID(f.getId());

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<User> findFriends(User u) {
		List<User> friends = new LinkedList<User>();
		Connection conn = null;
		PreparedStatement stmt = null;
		User user = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			stmt = conn
					.prepareStatement("SELECT * FROM social_friend  WHERE userId = ?");
			stmt.setLong(1, u.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = findByID(rs.getLong("friendId"));				
				friends.add(user);
			}
			return friends;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}	

	@Override
	public User findFrienById(Long userId, Long friendId) {
		User user = null;
		Connection conn = null;
		PreparedStatement stmt = null;	
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			stmt = conn
					.prepareStatement("SELECT * FROM social_friend WHERE userId = ? and friendId = ?");
			stmt.setLong(1,userId);
			stmt.setLong(2,friendId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = findByID(rs.getLong("friendId"));			
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}

	@Override
	public List<Post> getPostsByFriends(User u) {
		List<Post> posts = new LinkedList<Post>();
		Connection conn = null;
		PreparedStatement stmt = null;		
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			stmt = conn
					.prepareStatement("SELECT p.id FROM  social_post p, social_friend f "
										+ "WHERE p.userId = f.friendId "
										+ "AND f.userId = ? ORDER BY p.date");
			stmt.setLong(1, u.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Post post = ImplementationFinder.getImpl(PostDAO.class).findByID(rs.getLong("id"));				
				posts.add(post);
			}
			return posts;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}
	

}
