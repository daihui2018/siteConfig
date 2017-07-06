package com.johnjadd.id;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnjadd.MyEntity;

@Service
public class IdGenService {
	@Autowired
	private IdGenRepository idGenRepository;

	public void createInitDataIfEmpty() {
		if(idGenRepository.count()==0) {
			IdGen idgen = new IdGen((long)1, "site", (short)0, (long)0x1, true);
			idGenRepository.save(idgen);
		}
	}
	
	public boolean correctEntityId(MyEntity en) {
		boolean corrected = false;
		if(en.getId()==0) {
			en.setId(getUid(en.getClass().getSimpleName()));
			corrected = true;
		}
		return corrected;
	}

	private Long getUid(String entityName) {
		createInitDataIfEmpty();
		
		Long freeid = new Long(-1);
		IdGen idgen = idGenRepository.findOneByEntityName(entityName);
		if(idgen!=null) {
			freeid = idgen.getFreeId();
			if(idgen.isMax()) {
				idgen.incFreeId();				
				idGenRepository.save(idgen);
			}else {
				idGenRepository.delete(idgen);
			}
		}
		return freeid;
	}

	public List<IdGen> getAllIdGens() {
		List<IdGen> idGens = new ArrayList<>();
		idGenRepository.findAll().forEach(idGens::add);
		return idGens;
	}

	public void saveIdGen(IdGen idGen) {
		idGenRepository.save(idGen);
	}
}
