package br.com.fatec.socialnet.core.service;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Comment;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.entity.Post;
import br.com.fatec.socialnet.api.service.CommentService;
import br.com.fatec.socialnet.api.service.LikeService;
import br.com.fatec.socialnet.api.service.PostService;
import br.com.fatec.socialnet.core.TimelineService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TimelineServiceImp implements TimelineService{	

	private PostService pService;
	private CommentService cService;
	private LikeService lService;
	
	public TimelineServiceImp(){	
		pService = ImplementationFinder.getImpl(PostService.class);
		cService = ImplementationFinder.getImpl(CommentService.class);	
		lService = ImplementationFinder.getImpl(LikeService.class);	
	}

	public List<Post> getPostsByUser(Long id){
		return this.pService.getPostsByUser(id);
	}

	@Override
	public List<Comment> getCommentsByPost(Long id) {
		return this.cService.getCommentsByPost(id);
	}

	@Override
	public List<Like> getLikesByPost(Long id) {
		return this.lService.getLikesByPost(id);
	}

	@Override
	public List<Like> getLikesByComment(Long id) {
		return this.lService.getLikesByComment(id);
	}	

}
