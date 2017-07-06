package com.johnjadd.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cfg/site")
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping(method=RequestMethod.GET, value="")
	public List<SimpleSite> getAllSites(){
		return siteService.getAllSimple();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public Site getSite(@PathVariable("id") Long id){
		return siteService.getOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public void saveSite(@RequestBody Site site){
		siteService.save(site);
	}
	
	@RequestMapping(method=RequestMethod.PATCH, value="")
	public void patchSite(@RequestBody Site site){
		siteService.patch(site);
	}
	
}


