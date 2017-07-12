package com.johnjadd.var;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnjadd.dev.Dev;
import com.johnjadd.dev.DevService;
import com.johnjadd.util.Until;

@Service
public class VarService {
	@Autowired
	private VarRepository varRepository;
	@Autowired
	private DevService devService;

	public List<Var> getAll(Long devId) {
		List<Var> vars = new ArrayList<>();
		varRepository.findByDevId(devId).forEach(vars::add);
		return vars;
	}

	public Var get(Long id) {
		return varRepository.findOne(id);
	}
	
	public void save(Long devId, Var var) {
		Dev dev = devService.getOne(devId);
		if(dev==null) return;
		
		var.setDev(dev);
		if(correctId(var)) {
			varRepository.save(var);
		}
	}
	/*
	 * if return false,the varID is outoflaw 
	*/
	public boolean correctId(Var var) {
		Long minVarId = (var.getDev().getId())<<16;
		Long maxVarId = (var.getDev().getId()+1)<<16;
		if(var.getId() < minVarId) {
			var.setId(minVarId + var.getId());
		}
		
		if(var.getId() > maxVarId) {
			return false;
		}
		return true;
	}
	
	public void patch(Long devId, Var var) {
		if(var==null) return;
		
		if(fillNullProperties(var)) {
			if(var.getDev().getId().equals(devId)){
				varRepository.save(var);
			}
		}
	}
	
	public boolean fillNullProperties(Var var) {
		if(var==null) return false;
		
		Var varExisted = varRepository.findOne(var.getId());
		if(varExisted==null) return false;

		Until.copyNonNullProperties(var, varExisted);
		Until.copyProperties(varExisted, var);

		return true;
	}

	public void delete(Long id) {
		varRepository.delete(id);
	}

	public void deleteByDev(Long devId) {
		varRepository.deleteByDevId(devId);
	}

	/*public void delete(ListVarIds listVarIds) {
		Long siteId = listVarIds.getSiteId();
		Long devId = listVarIds.getDevId();

		Dev dev = devService.getOne(devId);
		if(dev==null) return;
		
		for(Long id: listVarIds.getIds()){
			if(devService.isVarBelongs(dev, id)) {
				varRepository.delete(id);
			}
		}
	}*/

	

}
