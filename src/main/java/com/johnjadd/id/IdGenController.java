package com.johnjadd.id;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/cfg/id")
@RestController
public class IdGenController {
	
	@Autowired
	private IdGenService idGenService;
	
	@RequestMapping(method=RequestMethod.GET, value="")
	public List<IdGen> getAllIdGens(){
		return idGenService.getAllIdGens();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public void saveSite(@RequestBody IdGen idGen){
		idGenService.saveIdGen(idGen);
	}
	
}
