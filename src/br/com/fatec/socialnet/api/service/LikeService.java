package br.com.fatec.socialnet.api.service;

import java.util.List;

import br.com.fatec.socialnet.api.entity.Like;

public interface LikeService {
	
	Like save(Like c);
	
	List< Like> findAll();  //READ
	
	Like findByID(Long id); //READ]
	
	Like update(Like c); //UPDATE
	
	void delete(Like c); //DELETE
	
	List<Like> getLikesByType(String type,Long typeId);	
	List<Like> getLikesByPost(Long id);
	List<Like> getLikesByComment(Long id);
	
	void removeLikesByUser(Long id);

}
