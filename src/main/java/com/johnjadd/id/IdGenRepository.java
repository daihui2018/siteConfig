package com.johnjadd.id;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdGenRepository extends CrudRepository<IdGen, Long> {

	IdGen findOneByEntityName(String entityName);
	
}
