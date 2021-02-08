package project.farmease.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.dto.UserProfile;
import project.farmease.farmeasyexception.FarmeasyException;
import project.farmease.pojo.User;

@Transactional
@Service
public class EditProfileService {
	
	Logger logger = LogManager.getLogger(EditProfileService.class);
	
	@Autowired
	private UserRepo userRepo;
	
	private Response response;
	private Optional<User> user;
	
	public Response doEditProfile(UserProfile userProfile)
	{
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
			logger.debug(e.getMessage(),e);
			throw new FarmeasyException("Error in edit profile!", e);
		}

		if (isupdatesuccessful!=0) 
		{
			response.setStatus(1);
			response.setMessage("Profile edited successfully");
		}
		
		return response;
	}

	public Optional<User> getprofile(String username) {
		
		Optional<User> user = null;

		try {
			user = userRepo.findById(username);
			logger.debug(user.get().getFname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage(),e);
			throw new FarmeasyException("Error in fetching profile details!", e);
		}

		if (user.isPresent() && user!=null) 
		{
			this.user = user;
		}
		
		return this.user;
	}

}
