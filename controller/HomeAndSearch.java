package project.farmease.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.models.Hostuser;
import project.farmease.repositories.HostuserRepo;

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

	public List<Hostuser> searchforservice(String equipment, String location) 
	{
		l = new ArrayList<Hostuser>();
		
		Hostuser h1 = new Hostuser("vish@email","tractor", "kubota", "ploughing", "pune", 222, null);
		Hostuser h2 = new Hostuser("vish@email","harvester", "kubota", "harvesting", "blr", 222, null);
		Hostuser h3 = new Hostuser("vish@email","tractor", "kubota", "ploughing", "blr", 222, null);
		
		//assume this result came from db
		l.add(h1);
		l.add(h2);
		l.add(h3);
		
		List<Hostuser> delete = new ArrayList<Hostuser>();
		for(Hostuser h:l)
		{
			//log.debug(equipment+" "+location);
			if(!((h.getEquipmenttype().equalsIgnoreCase(equipment)) && (h.getLocation().equalsIgnoreCase(location))))
			{
				//log.debug("condition satisfied got in");
				delete.add(h);
			}
		}
		l.removeAll(delete);
		
		//log.debug("list members got "+l.size());
		return l;
	}

	public int sum(int i, int j) 
	{
		return i+j;
	}

	
	//poc on test case
	
}
