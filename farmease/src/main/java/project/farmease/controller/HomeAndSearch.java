package project.farmease.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.dao.HostuserRepo;
import project.farmease.dao.UserRepo;
import project.farmease.dto.Hostdto;
import project.farmease.dto.Response;
import project.farmease.pojo.Hostuser;
import project.farmease.pojo.HostuserId;

 
@RestController
@RequestMapping("/services")  
public class HomeAndSearch {
	
	Logger logger = LogManager.getLogger(HomeAndSearch.class);
	@Autowired
	private UserRepo userRepo;
	private HostuserRepo hostuserRepo;
	List<Hostuser> l;
	
	@Autowired
	public void fun(HostuserRepo h)
	{
		hostuserRepo=h;
		//logger.debug("HostuserRepo autowired");
	}

	@CrossOrigin("http://localhost:4200")
	@PostMapping("/searchserv")
	public List<Hostuser> searchforservice(@RequestBody Hostuser hostuser) 
	{
		l = new ArrayList<Hostuser>();
		
//		Hostuser h1 = new Hostuser("vish@email","tractor", "kubota", "ploughing", "pune", 222, null);
//		Hostuser h2 = new Hostuser("vish@email","harvester", "kubota", "harvesting", "blr", 222, null);
//		Hostuser h3 = new Hostuser("vish@email","tractor", "kubota", "ploughing", "blr", 222, null);
		
		//assume this result came from db
//		l.add(h1);
//		l.add(h2);
//		l.add(h3);
		
		l = hostuserRepo.findmatchingservice(hostuser.getEquipmenttype(),hostuser.getLocation());
		//logger.debug(hostdto.getEquipmenttype(),hostdto.getLocation());
		
		List<Hostuser> delete = new ArrayList<Hostuser>();
		
		for(Hostuser h:l)
		{
			//log.debug(equipment+" "+location);
			if(!((h.getEquipmenttype().equalsIgnoreCase(hostuser.getEquipmenttype())) && (h.getLocation().equalsIgnoreCase(hostuser.getLocation()))))
			{
				//log.debug("condition satisfied got in");
				delete.add(h);
			}
		}
		l.removeAll(delete);
		
		//log.debug("list members got "+l.size());
		return l;
	}
	
	@PostMapping("/insert")
	public Response registerhost(@RequestBody Hostuser hostuser) {
		
		Response resp = new Response(0,"User Already Exists!");
		//db data assume
//		Hostuser host = new Hostuser("a@b","tractor", "kubota", "ploughing", "pune", 222, null);
		
		Boolean isUserPresent = false;
		Boolean isHostUserPresent = false;
		
		try {
			isUserPresent=userRepo.existsById(hostuser.getHostemail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Invalid Operation!");
		}
		
		//if(!(host.getHostemail().equals(hostuser.getHostemail())))
		if(isUserPresent)
		{
			isHostUserPresent=hostuserRepo.existsByHostemail(hostuser.getHostemail());
			
			if(isHostUserPresent)
			{
				HostuserId id = new HostuserId(hostuser.getHostemail(), hostuser.getEquipmenttype(), hostuser.getManufacturer(), hostuser.getServicetype(), hostuser.getLocation());
				
				Boolean isHostpresent=false;
				
				isHostpresent = hostuserRepo.existsById(id);
				
				if (!isHostpresent) {
					hostuserRepo.save(hostuser);
					resp.setStatus(1);
					resp.setMessage("Inserted successfully!");
				}
			}
			else
			{
				hostuserRepo.save(hostuser);
				resp.setStatus(1);
				resp.setMessage("Inserted successfully!");
			}
		}
		else
		{
			resp.setMessage("You need to signup first!!!");
		}
		
		return resp;
	}

	//poc on test case
	public int sum(int i, int j) 
	{
		return i+j;
	}	
}
