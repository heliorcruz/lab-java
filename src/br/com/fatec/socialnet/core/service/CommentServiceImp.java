package br.com.fatec.socialnet.core.service;

import java.util.List;

import br.com.fatec.socialnet.api.dao.CommentDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.service.CommentService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class CommentServiceImp implements CommentService{
	
	private CommentDAO dao;
	
	public CommentServiceImp(){		
		this.dao = ImplementationFinder.getImpl(CommentDAO.class);
	}

	@Override
	public Comment save(Comment c) {
		return dao.save(c);
	}

	@Override
	public List<Comment> findAll() {
		return dao.findAll();
	}

	@Override
	public Comment findByID(Long id) {
		return dao.findByID(id);
	}

	@Override
	public Comment update(Comment c) {
		return dao.update(c);
	}

	@Override
	public void delete(Comment c) {
		dao.removeLikesByComment(c.getId());
		dao.delete(c);		
	}

	@Override
	public List<Comment> getCommentsByPost(Long id) {
		return dao.getCommentsByPost(id);
	}

	@Override
	public Comment getCommentByPost(Long id) {
		return dao.getCommentByPost(id);
	}

}
