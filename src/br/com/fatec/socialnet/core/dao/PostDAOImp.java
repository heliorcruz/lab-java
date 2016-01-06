package br.com.fatec.socialnet.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class PostDAOImp implements PostDAO {

	Connection conn;
	PreparedStatement sql;

	public PreparedStatement openConnSQL(String sqlString) throws SQLException {

		this.conn = ConfigDBMapper.getDefaultConnection();

		return conn.prepareStatement(sqlString);
	}

	@Override
	public Post save(Post p) {

		try {

			sql = openConnSQL("INSERT INTO " + Post.TABLE_NAME
					+ " VALUES(?,?,?,?,?)");

			Long id = GeradorIdService.getInstance().getNextId(Post.TABLE_NAME);

			// set arguments
			sql.setLong(1, id);
			sql.setString(2, p.getTopic());
			sql.setString(3, p.getMessage());
			sql.setDate(4, p.getDate());
			sql.setLong(5, p.getUserId());
			sql.execute();

			return this.findByID(id);

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Post> findAll() {

		try {

			sql = openConnSQL("SELECT * FROM " + Post.TABLE_NAME);

			ResultSet rs = sql.executeQuery();

			return createList(rs);

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	public List<Post> createList(ResultSet rs) throws SQLException {

		List<Post> list = new LinkedList<Post>();

		while (rs.next()) {

			list.add(createPost(rs));
		}
		return list;

	}

	public Post createPost(ResultSet rs) throws SQLException {

		return new Post(rs.getLong(Post.COL_ID), rs.getString(Post.COL_TOPIC),
				rs.getString(Post.COL_MESSAGE), rs.getDate(Post.COL_DATE),
				rs.getLong(Post.COL_USERID));
	}

	@Override
	public Post findByID(Long id) {

		try {

			sql = openConnSQL("SELECT * FROM " + Post.TABLE_NAME
					+ " WHERE id = ?");
			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();

			if (rs.next())
				return createPost(rs);
			else
				return null;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	@Override
	public Post update(Post p) {

		try {

			sql = openConnSQL(" UPDATE " + Post.TABLE_NAME + " " + " SET "
					+ Post.COL_TOPIC + "=?," + Post.COL_MESSAGE + "=?,"
					+ Post.COL_DATE + "=?," + Post.COL_USERID + "=?"
					+ " WHERE " + Post.COL_ID + "=?");

			// set arguments
			sql.setString(1, p.getTopic());
			sql.setString(2, p.getMessage());
			sql.setDate(3, p.getDate());
			sql.setLong(4, p.getUserId());
			sql.setLong(5, p.getId());
			sql.execute();

			return this.findByID(p.getId());

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void delete(Post p) {

		try {

			sql = openConnSQL("DELETE FROM " + Post.TABLE_NAME + " "
					+ " WHERE " + Post.COL_ID + "=?");

			// set arguments
			sql.setLong(1, p.getId());
			sql.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Post> getPostsByUser(Long id) {

		try {

			sql = openConnSQL("SELECT * FROM social_post WHERE userId = ? ORDER BY date desc");
			sql.setLong(1, id);
			ResultSet result = sql.executeQuery();

			return createList(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
	}

	@Override
	public Post getPostByUser(Long id, Long userId) {
		try {

			sql = openConnSQL("SELECT * FROM " + Post.TABLE_NAME + " WHERE "
					+ Post.COL_USERID + "= ? and id = ?");
			sql.setLong(1, userId);
			sql.setLong(2, id);

			ResultSet rs = sql.executeQuery();

			if (rs.next())
				return createPost(rs);
			else
				return null;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	@Override
	public void removeLikesByPost(Long id) {

		try {

			sql = openConnSQL("DELETE FROM " + Like.TABLE_NAME + " "
					+ " WHERE " + Post.COL_ID + "=?");

			// set arguments
			sql.setLong(1,id);
			sql.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	@Override
	public void removeCommentsByPost(Long id) {

		try {

			sql = openConnSQL("DELETE FROM " + Comment.TABLE_NAME + " "
					+ " WHERE " + Post.COL_ID + "=?");

			// set arguments
			sql.setLong(1,id);
			sql.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	@Override
	public void removePostsByUser(Long id) {
		try {

			sql = openConnSQL("DELETE FROM " + Post.TABLE_NAME + " "
					+ " WHERE " + Post.COL_USERID + "=?");

			// set arguments
			sql.setLong(1,id);
			sql.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
		
	}

}
