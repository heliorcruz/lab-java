package br.com.fatec.socialnet.core;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;


public interface TimelineService {
	
	public List<Post> getPostsByUser(Long id);	
	public List<Comment> getCommentsByPost(Long id);
	public List<Like> getLikesByPost(Long id);
	public List<Like> getLikesByComment(Long id);

}
