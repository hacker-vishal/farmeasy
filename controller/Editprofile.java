package project.farmease.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.models.Address;
import project.farmease.models.Response;
import project.farmease.models.User;
import project.farmease.models.UserProfile;
import project.farmease.repositories.AddressRepo;
import project.farmease.repositories.UserRepo;

@CrossOrigin("*")
@RestController
public class Editprofile {
	
	Logger logger = LogManager.getLogger(Editprofile.class);
	private Response response;
	private UserRepo userRepo;
	private AddressRepo addressRepo;
	
	@Autowired
	public void profilewiring(UserRepo userRepo, AddressRepo addressRepo)
	{
		this.userRepo = userRepo;
		this.addressRepo = addressRepo;
		logger.debug("autowired");
	}

	@PutMapping("/")
	public Response doeditprofile(@RequestBody UserProfile userProfile) {
		
		response = new Response(0,"Profile edition failed");
		
		//let us update profile inside the db first through dummy logic
//		int isupdatesuccessful = 0;
		
		//db logic to check if row got updated or not
//		isupdatesuccessful = 1;
		int a = 0;
		int b = 0;

		try {
			a = userRepo.updateforuser(userProfile.getEmail(),userProfile.getFname(),userProfile.getLname(),userProfile.getMobileno());
			logger.debug(a);
			
			b = addressRepo.updateforaddress(userProfile.getEmail(),userProfile.getLocation(),userProfile.getCity(),userProfile.getState(),userProfile.getZipcode());
			logger.debug(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}

		if (a!=0 && b!=0) 
		{
			response.setStatus(1);
			response.setMessage("Profile edited successfully");
		}
		
		return response;
	}

}
