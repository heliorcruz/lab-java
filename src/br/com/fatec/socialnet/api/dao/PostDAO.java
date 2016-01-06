package br.com.fatec.socialnet.api.dao;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Post;

public interface PostDAO {

	Post save(Post p);
	
	List<Post> findAll();  //READ
	
	Post findByID(Long id); //READ]
	
	Post update(Post p); //UPDATE
	
	void delete(Post p); //DELETE
	
	List<Post> getPostsByUser(Long id);
	Post getPostByUser(Long id,Long userId);
	
	void removeLikesByPost(Long id);
	void removeCommentsByPost(Long id);
	
	void removePostsByUser(Long id);
	
}
