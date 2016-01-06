package br.com.fatec.socialnet.api.service;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Comment;

public interface CommentService {
	
	Comment save(Comment c);
	
	List< Comment> findAll();  //READ
	
	Comment findByID(Long id); //READ]
	
	Comment update( Comment c); //UPDATE
	
	void delete( Comment c); //DELETE
	
	List< Comment> getCommentsByPost(Long id);
	Comment getCommentByPost(Long id);
	

}
