package br.com.fatec.socialnet.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.socialnet.api.dao.LikeDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class LikeDAOImp implements LikeDAO {

	Connection conn;
	PreparedStatement sql;

	public PreparedStatement openConnSQL(String sqlString) throws SQLException {

		this.conn = ConfigDBMapper.getDefaultConnection();

		return conn.prepareStatement(sqlString);
	}

	@Override
	public Like save(Like l) {

		try {

			sql = openConnSQL("INSERT INTO " + Like.TABLE_NAME
					+ " VALUES(?,?,?,?)");

			Long id = GeradorIdService.getInstance().getNextId(Like.TABLE_NAME);

			// set arguments
			sql.setLong(1, id);
			sql.setLong(2, l.getUserId());
			sql.setString(3, l.getType());
			sql.setLong(4, l.getTypeId());
			sql.execute();

			return this.findByID(id);

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<Like> findAll() {

		try {

			sql = openConnSQL("SELECT * FROM " + Like.TABLE_NAME);

			ResultSet rs = sql.executeQuery();

			return createList(rs);

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	public List<Like> createList(ResultSet rs) throws SQLException {

		List<Like> list = new LinkedList<Like>();

		while (rs.next()) {

			list.add(createLike(rs));
		}
		return list;

	}

	public Like createLike(ResultSet rs) throws SQLException {

		return new Like(rs.getLong(Like.COL_ID), rs.getLong(Like.COL_USERID),
				rs.getString(Like.COL_TYPE), rs.getLong(Like.COL_TYPEID));
	}

	@Override
	public Like findByID(Long id) {

		try {

			sql = openConnSQL("SELECT * FROM " + Like.TABLE_NAME
					+ " WHERE id = ?");
			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();

			if (rs.next())
				return createLike(rs);
			else
				return null;

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	@Override
	public Like update(Like p) {

		try {

			sql = openConnSQL(" UPDATE " + Like.TABLE_NAME + " " + " SET "
					+ Like.COL_USERID + "=?," + Like.COL_TYPE + "=?,"
					+ Like.COL_TYPEID + "=?" + " WHERE " + Like.COL_ID + "=?");

			// set arguments
			sql.setLong(1, p.getUserId());
			sql.setString(2, p.getType());
			sql.setLong(3, p.getTypeId());
			sql.setLong(4, p.getId());
			sql.execute();

			return this.findByID(p.getId());

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public void delete(Like p) {

		try {

			sql = openConnSQL("DELETE FROM " + Like.TABLE_NAME + " "
					+ " WHERE " + Like.COL_ID + "=?");

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
	public List<Like> getLikesByPost(Long id) {
		return getLikesByType(Post.TABLE_NAME,id);
	}

	@Override
	public List<Like> getLikesByComment(Long id) {
		return getLikesByType(Comment.TABLE_NAME,id);
	}

	@Override
	public List<Like> getLikesByType(String type, Long typeId) {
		
		try {
			sql = openConnSQL("SELECT * FROM "+Like.TABLE_NAME+" WHERE typeId = ? and type = ?");
			sql.setLong(1, typeId);
			sql.setString(2, type);
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
	public void removeLikesByUser(Long id) {
		try {

			sql = openConnSQL("DELETE FROM " + Like.TABLE_NAME + " "
					+ " WHERE " + Like.COL_USERID + "=?");

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
