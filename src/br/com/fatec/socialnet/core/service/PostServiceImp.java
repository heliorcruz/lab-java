package br.com.fatec.socialnet.core.service;

import java.util.List;

import br.com.fatec.socialnet.api.dao.PostDAO;
import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.service.CommentService;
import br.com.fatec.socialnet.api.service.PostService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class PostServiceImp implements PostService{
	
	private PostDAO dao;
	
	public PostServiceImp(){
		this.dao = ImplementationFinder.getImpl(PostDAO.class);
	}

	@Override
	public Post save(Post Post) {
		Post nPost =  dao.save(Post);
		return dao.findByID(nPost.getId());
	}

	@Override
	public Post update(Post Post) {
		Post mPost = dao.update(Post);
		return dao.findByID(mPost.getId());
	}

	@Override
	public void delete(Post post) {		
		dao.removeLikesByPost(post.getId());		
		removeCommentsByPostCascadeLikes(post.getId());
		dao.delete(post);
	}

	@Override
	public List<Post> findAll() {
		return dao.findAll();
	}

	@Override
	public Post findById(Long id) {
		return dao.findByID(id);
	}

	@Override
	public List<Post> getPostsByUser(Long id) {
		return dao.getPostsByUser(id);
	}

	@Override
	public Post getPostByUser(Long id, Long userId) {
		return dao.getPostByUser(id, userId);
	}

	@Override
	public void removeCommentsByPostCascadeLikes(Long id) {
		
		CommentService service = ImplementationFinder.getImpl(CommentService.class);
		List<Comment> comments = service.getCommentsByPost(id);		
		for (Comment c: comments){			
			service.delete(c);
		}		
	}

}
