package com.johnjadd.var;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cfg/var")
public class VarController {

	@Autowired
	private VarService varService;
	
	@RequestMapping(method=RequestMethod.GET, value="/bydev/{devId}")
	public List<Var> getAll(@PathVariable("devId") Long devId){
		return varService.getAll(devId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public Var get(@PathVariable("id") Long id){
		return varService.get(id);
	}
	
	@RequestMapping(method=RequestMethod.PATCH, value="/{devId}")
	public void patch(@PathVariable("devId")Long devId, @RequestBody Var var){
		varService.patch(devId, var);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void delete(@PathVariable("id") Long id){
		varService.delete(id);
	}
	
	/*@RequestMapping(method=RequestMethod.DELETE, value="")
	public void delete(@RequestBody ListVarIds listVarIds){
		System.out.println(listVarIds.getIds().toString());
		varService.delete(listVarIds);
	}*/
	
	@RequestMapping(method=RequestMethod.POST, value="/{devId}")
	public void save(@PathVariable("devId") Long devId, @RequestBody Var var){
		varService.save(devId, var);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/bydev/{devId}")
	public void deleteByDev(@PathVariable("devId") Long devId){
		varService.deleteByDev(devId);
	}
	
}
