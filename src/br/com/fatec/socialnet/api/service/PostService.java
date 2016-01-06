package br.com.fatec.socialnet.api.service;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Post;


public interface PostService {
	
	public Post save(Post Post);

	public Post update(Post Post);

	public void delete(Post Post);

	public List<Post> findAll();

	public Post findById(Long id);
	
	List<Post> getPostsByUser(Long id);
	Post getPostByUser(Long id,Long userId);
	
	void removeCommentsByPostCascadeLikes(Long id);

}
