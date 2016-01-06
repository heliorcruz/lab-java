package br.com.fatec.socialnet.core.service;

import java.util.List;

import br.com.fatec.socialnet.api.dao.LikeDAO;
import br.com.fatec.socialnet.api.entity.Like;
import br.com.fatec.socialnet.api.service.LikeService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class LikeServiceImp implements LikeService{
	
	private LikeDAO dao;
	
	public LikeServiceImp(){
		this.dao = ImplementationFinder.getImpl(LikeDAO.class);
	}

	@Override
	public Like save(Like like) {
		return dao.save(like);
	}

	@Override
	public List<Like> findAll() {
		return dao.findAll();
	}

	@Override
	public Like findByID(Long id) {
		return dao.findByID(id);
	}

	@Override
	public Like update(Like like) {
		return dao.update(like);
	}

	@Override
	public void delete(Like like) {
		dao.delete(like);		
	}

	@Override
	public List<Like> getLikesByType(String type, Long typeId) {
		return dao.getLikesByType(type,typeId);
	}

	@Override
	public List<Like> getLikesByPost(Long id) {
		return dao.getLikesByPost(id);
	}

	@Override
	public List<Like> getLikesByComment(Long id) {
		return dao.getLikesByComment(id);
	}

	@Override
	public void removeLikesByUser(Long id) {
		dao.removeLikesByUser(id);		
	}

}
