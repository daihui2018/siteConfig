package com.johnjadd.dev;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnjadd.util.Until;
import com.johnjadd.var.Var;
import com.johnjadd.var.VarService;

@Service
public class DevService {
	@Autowired
	private DevRepository devRepository;
	
	@Autowired
	private VarService varService;
		
	//@Autowired
	//private SiteService siteService;

	public List<Dev> getAll(Long siteId) {
		List<Dev> devs = new ArrayList<>();
		//devRepository.findAllBySiteId(siteId).forEach(devs::add);
		devRepository.findBySiteId(siteId).forEach(devs::add);
		return devs;
	}

	public Dev getOne(Long id) {
		return devRepository.findOne(id);
	}
	
	public Dev patch(Long parentId, Dev dev) {
		if(dev==null) return null;
		
		dev.setChildren(null);
		fillNullProperties(dev);
		
		if(dev.hasOffSpring(parentId)) {
			return null;
		}
		
		if(dev.getParent()!=null) {
			if(!dev.getParent().getId().equals(parentId)) {
				Dev parent = getOne(parentId);
				dev.setParent(parent);
			}
		}
		
		devRepository.save(dev);
		return dev;
	}
	
	public Dev save(Long parentId, Dev dev) {
		Dev parent = getOne(parentId);
		return save(parent, dev);
	}
	
	private Dev save(Dev parent, Dev dev) {
		setDevsParent(dev, parent);
		
		setVarsDev(dev);
		if(correctId(dev)) {
			////????????????how to make transactions
			/*try {
				devRepository.delete(dev.getId());
			}catch(Exception e) {
				System.out.println(e);
			}*/
			delete(dev.getId());
			
			if(dev.getChildren() == null) {
				devRepository.save(dev);
			}else {
				Dev dev2Save = new Dev();
				Until.copyProperties(dev, dev2Save);
				dev2Save.setChildren(null);
				
				devRepository.save(dev2Save);
				
				for(Dev child : dev.getChildren()) {
					save(dev, child);
				}				
			}
			return dev;
		}		
		return null;
	}
		
	public void setDevsParent(Dev dev, Dev parent) {
		if(dev == null) return;
		dev.setParent(parent);

		/*if(dev.getChildren() == null) return;
		for(Dev dd : dev.getChildren()) {
			setDevsParent(dd, dev);
		}*/
	}
	
	public void setVarsDev(Dev dev) {
		if(dev == null) return;
		if(dev.getVars() == null) return;
		for (Var var : dev.getVars()) {
			var.setDev(dev);
		};
		
		/*if(dev.getChildren() == null) return;
		for(Dev dd : dev.getChildren()) {
			setVarsDev(dd);
		}*/
	}
	
	public boolean correctId(Dev dev) {
		/*Long minId = (dev.getSite().getId())<<16;
		Long maxId = (dev.getSite().getId()+1)<<16;

		if(dev.getId() < minId) {
			dev.setId(minId + dev.getId());
		}
		
		if(dev.getId() > maxId) {
			return false;
		}*/
		
		if(dev.getVars() != null) {
			List<Var> illegalIdVars = new ArrayList<>();
			for (Var var : dev.getVars()) {
				if(varService.correctId(var)==false) {
					illegalIdVars.add(var);
				}
			}
			dev.getVars().removeAll(illegalIdVars);
		}
		
		/*if(dev.getChildren() != null) {
			for(Dev dd : dev.getChildren()) {
				if(correctId(dd) == false) {
					return false;
				}	
			}
		}*/
		return true;
	}
	
	private boolean fillNullProperties(Dev dev) {
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
		Dev dev = devRepository.findOne(id);
		return delete(dev);
	}
	
	private Long delete(Dev dev) {
		if(dev!=null) {
			for(Dev child : dev.getChildren()) {
				delete(child);
			}			
			devRepository.delete(dev.getId());
			return dev.getId();
		}
		return (long)-1;
	}

	/*public List<SimpleDev> getAllSimple() {
		List<Dev> devs = new ArrayList<>();
		devRepository.findAll().forEach(devs::add);
		
		List<SimpleDev> simpleDevs = new ArrayList<>();
		for (Dev dev : devs) {
			simpleDevs.add(new SimpleDev(dev.getId(), dev.getLocalId(),dev.getName()));
		}
		return simpleDevs;
	}*/
	
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
