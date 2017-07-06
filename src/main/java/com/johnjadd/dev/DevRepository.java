package com.johnjadd.dev;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DevRepository extends CrudRepository<Dev, Long>{
	
	//public List<Dev> findAllBySiteId(String siteId);

	//@Modifying
	//@Query(value="delete from dev_config where site_id = ?")
	@Transactional 
	public void deleteBySiteId(Long siteId);

	public List<Dev> findBySiteId(Long siteId);

}
