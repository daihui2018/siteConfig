package com.johnjadd.dev;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnjadd.site.Site;
import com.johnjadd.site.SiteService;
import com.johnjadd.util.Until;
import com.johnjadd.var.Var;
import com.johnjadd.var.VarService;

@Service
public class DevService {
	@Autowired
	private DevRepository devRepository;
	
	@Autowired
	private VarService varService;
		
	@Autowired
	private SiteService siteService;

	public List<Dev> getAll(Long siteId) {
		List<Dev> devs = new ArrayList<>();
		//devRepository.findAllBySiteId(siteId).forEach(devs::add);
		devRepository.findBySiteId(siteId).forEach(devs::add);
		return devs;
	}

	public Dev getOne(Long id) {
		return devRepository.findOne(id);
	}
	
	public Dev patch(Dev dev) {
		if(dev==null) return null;
		
		fillNullProperties(dev);

		if(dev!=null) {
			devRepository.save(dev);
		}
		
		return dev;
	}
	
	public Dev save(Long siteId, Dev dev) {
		Site site = siteService.getOne(siteId);
		if(site==null) return null;
		
		dev.setSite(site);
		setDevInVars(dev);	
		if(correctId(dev)) {
			////????????????how to make transactions
			try {
				devRepository.delete(dev.getId());
			}catch(Exception e) {
				System.out.println(e);
			}
			devRepository.save(dev);
			return dev;
		}
		
		return null;
	}
	
	public void setDevInVars(Dev dev) {
		if(dev.getVars()==null) return;
		for (Var var : dev.getVars()) {
			var.setDev(dev);
		}
	}
	
	public boolean correctId(Dev dev) {
		Long minId = (dev.getSite().getId())<<16;
		Long maxId = (dev.getSite().getId()+1)<<16;

		if(dev.getId() < minId) {
			dev.setId(minId + dev.getId());
		}
		
		if(dev.getId() > maxId) {
			return false;
		}
		
		if(dev.getVars() != null) {
			List<Var> illegalIdVars = new ArrayList<>();
			for (Var var : dev.getVars()) {
				if(varService.correctId(var)==false) {
					illegalIdVars.add(var);
				}
			}
			dev.getVars().removeAll(illegalIdVars);
		}
		return true;
	}
	
	public boolean fillNullProperties(Dev dev) {
		if(dev==null) return false;
		
		Dev existed = devRepository.findOne(dev.getId());
		if(existed==null) return false;

		if(dev.getVars() != null) {
			List<Var> notInDBVars = new ArrayList<>();
			for (Var var : dev.getVars()) {
				if(varService.fillNullProperties(var)==false) {
					notInDBVars.add(var);
				}
			}
			
			dev.getVars().removeAll(notInDBVars);
		}
		
		Until.copyNonNullProperties(dev, existed);
		Until.copyProperties(existed, dev);
		
		return true;
	}

	public Long delete(Long id) {
		Dev existed = devRepository.findOne(id);
		if(existed!=null) {
			devRepository.delete(id);
			return id;
		}
		return (long)-1;
	}

	public void deleteBySite(Long siteId) {
		devRepository.deleteBySiteId(siteId);
	}

	
	public List<SimpleDev> getAllSimple() {
		List<Dev> devs = new ArrayList<>();
		devRepository.findAll().forEach(devs::add);
		
		List<SimpleDev> simpleDevs = new ArrayList<>();
		for (Dev dev : devs) {
			simpleDevs.add(new SimpleDev(dev.getId(), dev.getLocalId(),dev.getName()));
		}
		return simpleDevs;
	}
	
	public boolean isVarBelongs(Dev dev, Long varId) {
		if(dev==null) return false;
		
		for(Var vv: dev.getVars()){			
			if(vv.getId().equals(varId) ){//no == , will get wrong
				return true;
			}
		}
		return false;
	}

}
