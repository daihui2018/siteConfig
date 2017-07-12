package com.johnjadd.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cfg/dev")
public class DevController {
	
	@Autowired
	private DevService devService;
	
	/*@RequestMapping(method=RequestMethod.GET, value="")
	public List<SimpleDev> getAllSimple(){
		return devService.getAllSimple();
	}*/
	
	/*@RequestMapping(method=RequestMethod.GET, value="/bysite/{siteId}")
	public List<Dev> getAll(@PathVariable("siteId") Long siteId){
		return devService.getAll(siteId);
	}*/
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public Dev get(@PathVariable("id") Long id){
		Dev dev = devService.getOne(id);
		return dev;
	}
	
	@RequestMapping(method=RequestMethod.PATCH, value="/{parentId}")
	public Dev patch(@PathVariable("parentId")Long parentId, @RequestBody Dev dev){
		return devService.patch(parentId, dev);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public Long delete(@PathVariable("id") Long id){
		return devService.delete(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{parentId}")
	public Dev save(@PathVariable("parentId")Long parentId, @RequestBody Dev dev){
		return devService.save(parentId, dev);
	}
	
	/*@RequestMapping(method=RequestMethod.DELETE, value="/bysite/{siteId}")
	public void deleteBySite(@PathVariable("devId") Long siteId){
		devService.deleteBySite(siteId);
	}*/
	
}
