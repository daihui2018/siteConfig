package com.johnjadd.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(method=RequestMethod.GET, value="")
	public List<SimpleDev> getAllSimple(){
		return devService.getAllSimple();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/bysite/{siteId}")
	public List<Dev> getAll(@PathVariable("siteId") Long siteId){
		return devService.getAll(siteId);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public Dev get(@PathVariable("id") Long id){
		return devService.getOne(id);
	}
	
	@RequestMapping(method=RequestMethod.PATCH, value="")
	public Dev patch(@RequestBody Dev dev){
		return devService.patch(dev);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public Long delete(@PathVariable("id") Long id){
		return devService.delete(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/bysite/{siteId}")
	public Dev save(@PathVariable("siteId") Long siteId, @RequestBody Dev dev){
		return devService.save(siteId, dev);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/bysite/{siteId}")
	public void deleteBySite(@PathVariable("devId") Long siteId){
		devService.deleteBySite(siteId);
	}
	
}
