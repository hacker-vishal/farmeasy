package project.farmease.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.models.Hostuser;
import project.farmease.models.HostuserRepo;

@CrossOrigin(origins = "*")
@RestController
public class HomeAndSearch {
	
	Logger log = LogManager.getLogger(HomeAndSearch.class);
	private HostuserRepo hurepo;
	List<Hostuser> l;
	
	@Autowired
	public void fun(HostuserRepo h)
	{
		hurepo=h;
		//log.debug("HostuserRepo autowired");
	}

	public List<Hostuser> searchforservice(String equipment, String location) {
		Hostuser h1 = new Hostuser("vish@email","tractor", "kubota", "ploughing", "pune", 222, null);
		Hostuser h2 = new Hostuser("vish@email","harvester", "kubota", "harvesting", "blr", 222, null);
		Hostuser h3 = new Hostuser("vish@email","tractor", "kubota", "ploughing", "blr", 222, null);
		
		l.add(h1);//from db
		l.add(h2);
		l.add(h3);
		
		for(Hostuser h:l)
		{
			if(h.getEquipmenttype()!="tractor" || h.getLocation()!="pune")
			{
				int index=-1;
				index = l.indexOf(h);
				l.remove(index);
			}		
		}	
		
		return l;
	}
}
