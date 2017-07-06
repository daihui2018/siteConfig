package com.johnjadd.var;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VarRepository extends CrudRepository<Var, Long>{

	public List<Var> findByDevId(Long devId);

	@Transactional
	public void deleteByDevId(Long devId);

}
