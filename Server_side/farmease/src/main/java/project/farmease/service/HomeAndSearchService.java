package project.farmease.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.dao.HostuserRepo;
import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.pojo.Hostuser;
import project.farmease.pojo.HostuserId;

@Transactional
@Service
public class HomeAndSearchService {
	
	Logger logger = LogManager.getLogger(HomeAndSearchService.class);
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private HostuserRepo hostuserRepo;
	
	public List<Hostuser> searchforservice(Hostuser hostuser) {
		
		List<Hostuser> l = new ArrayList<Hostuser>();
		
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
	
	
	public Response registerhost(Hostuser hostuser) {

		Response resp = new Response(0,"User Already Exists!");
		//db data assume
//		Hostuser host = new Hostuser("a@b","tractor", "kubota", "ploughing", "pune", 222, null);
		
		Boolean isUserPresent = false;
		Boolean isHostUserPresent = false;
		
		try {
			logger.debug(hostuser.getHostemail());
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
	
}
