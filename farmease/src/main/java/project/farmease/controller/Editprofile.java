package project.farmease.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.dao.AddressRepo;
import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.dto.UserProfile;
import project.farmease.pojo.Address;
import project.farmease.pojo.User;

@RequestMapping("/update")
@CrossOrigin
@RestController
public class Editprofile {
	
	Logger logger = LogManager.getLogger(Editprofile.class);
	private Response response;
	private UserRepo userRepo;
	private AddressRepo addressRepo;
	private Optional<User> user;
	
	@Autowired
	public void profilewiring(UserRepo userRepo)
	{
		this.userRepo = userRepo;
		//logger.debug("autowired");
	}

	@PostMapping("/")
	public Response doeditprofile(@RequestBody UserProfile userProfile) {
		
		response = new Response(0,"Profile edition failed");
		
		//let us update profile inside the db first through dummy logic
//		int isupdatesuccessful = 0;
		
		//db logic to check if row got updated or not
//		isupdatesuccessful = 1;
		int isupdatesuccessful = 0;

		try {
			isupdatesuccessful = userRepo.updateforuser(userProfile.getEmail(),userProfile.getFname(),userProfile.getLname(),userProfile.getMobileno());
			logger.debug(isupdatesuccessful);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}

		if (isupdatesuccessful!=0) 
		{
			response.setStatus(1);
			response.setMessage("Profile edited successfully");
		}
		
		return response;
	}
	
	@GetMapping("/details")
	public Optional<User> getprofile(@RequestParam String username) {
		
		Optional<User> user = null;

		try {
			user = userRepo.findById(username);
			logger.debug(user.get().getFname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
		}

		if (user.isPresent() && user!=null) 
		{
			this.user = user;
		}
		
		return this.user;
	}

}
