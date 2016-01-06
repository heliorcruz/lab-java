package br.com.fatec.socialnet.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.socialnet.api.dao.CommentDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class CommentDAOImp implements CommentDAO {

	Connection conn;
	PreparedStatement sql;

	public PreparedStatement openConnSQL(String sqlString) throws SQLException {

		this.conn = ConfigDBMapper.getDefaultConnection();

		return conn.prepareStatement(sqlString);
	}

	@Override
	public Comment save(Comment c) {

		try {
			sql = openConnSQL("INSERT INTO " + Comment.TABLE_NAME
					+ " VALUES(?,?,?,?,?)");

			Long id = GeradorIdService.getInstance().getNextId(
					Comment.TABLE_NAME);

			// set arguments
			sql.setLong(1, id);
			sql.setString(2, c.getMessage());
			sql.setDate(3, c.getDate());
			sql.setLong(4, c.getUserId());
			sql.setLong(5, c.getPostId());
			sql.execute();

			return this.findByID(id);

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Comment> findAll() {

		try {

			sql = openConnSQL("SELECT * FROM " + Comment.TABLE_NAME);

			ResultSet rs = sql.executeQuery();

			return createList(rs);

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	public List<Comment> createList(ResultSet rs) throws SQLException {

		List<Comment> list = new LinkedList<Comment>();

		while (rs.next()) {

			list.add(createComment(rs));
		}
		return list;

	}

	public Comment createComment(ResultSet rs) throws SQLException {

		return new Comment(rs.getLong(Comment.COL_ID),
				rs.getString(Comment.COL_MESSAGE),
				rs.getDate(Comment.COL_DATE), rs.getLong(Comment.COL_USERID),
				rs.getLong(Comment.COL_POSTID));
	}

	@Override
	public Comment findByID(Long id) {

		try {

			sql = openConnSQL("SELECT * FROM " + Comment.TABLE_NAME
					+ " WHERE id = ?");
			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();

			if (rs.next())
				return createComment(rs);
			else
				return null;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	@Override
	public Comment update(Comment p) {

		try {

			sql = openConnSQL(" UPDATE " + Comment.TABLE_NAME + " " + " SET "
					+ Comment.COL_MESSAGE + "=?," + Comment.COL_DATE + "=?,"
					+ Comment.COL_USERID + "=?," + Comment.COL_POSTID + "=?"
					+ " WHERE " + Comment.COL_ID + "=?");

			// set arguments
			sql.setString(1, p.getMessage());
			sql.setDate(2, p.getDate());
			sql.setLong(3, p.getUserId());
			sql.setLong(4, p.getPostId());
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
	public void delete(Comment p) {

		try {

			sql = openConnSQL("DELETE FROM " + Comment.TABLE_NAME + " "
					+ " WHERE " + Comment.COL_ID + "=?");

			// set arguments
			sql.setLong(1, p.getId());
			;
			sql.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Comment> getCommentsByPost(Long id) {

		try {

			sql = openConnSQL("SELECT * FROM social_Comment WHERE postId = ? ORDER BY date");
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
	public Comment getCommentByPost(Long id) {
		try {

			sql = openConnSQL("SELECT * FROM " + Comment.TABLE_NAME
					+ " WHERE postId = ?");
			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();

			if (rs.next())
				return createComment(rs);
			else
				return null;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void removeLikesByComment(Long id) {
		try {

			sql = openConnSQL("DELETE FROM " + Like.TABLE_NAME + " "
					+ " WHERE " + Comment.COL_ID + "=?");

			// set arguments
			sql.setLong(1,id);
			;
			sql.execute();

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
		
	}

}
