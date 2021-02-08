package project.farmease.service;

import java.util.Optional;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.dto.Userdto;
import project.farmease.farmeasyexception.FarmeasyException;
import project.farmease.pojo.User;

@Transactional
@Service
public class PswdworkService {
	
	Logger logger = LogManager.getLogger(PswdworkService.class);
	
	private Optional<User> user;
	private Response response;
	
	@Autowired
    private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Response passwdreset(String id) {
		
		//id will be checked with entries in db for match
//		user = new User("abc","",0);
		try {
			user = userRepo.findById(id);
		} catch (Exception e) {
			logger.error(e);
			throw new FarmeasyException("Error in resetting password!", e);
		}
		
		response = new Response(0, "User not found");
		
//		if(user.getEmail().equals(id)) 
		if(user.isPresent() && user.get().getEmail().equals(id))
		{
			//logger.debug("got in");
			//6 digit random number generation in java
			int random = new Random().nextInt(900000) + 100000;
			//logger.debug(random);
			
			//create trigger to set otp to null
			//db logic to add random number into table
			try {
				userRepo.createotp(random,id);
			} catch (Exception e) {
				logger.error(e);
				throw new FarmeasyException("Error in creating otp!", e);
			}
			
			//return the response
			response.setStatus(1);
			response.setMessage("User found");
		}
		return response;
	}

	public Integer getotp(String id) {

		Optional<User> u;
		try {
			u=userRepo.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new FarmeasyException("Error in getting otp!", e);
		}
		//logger.debug(user.get().getOtp());
		return u.get().getOtp();
	}

	public Response checkotp(int otp) {
		response = new Response(0, "OTP verification failed");
		
		//assume it came from db
//		User user = new User("abc","",123456,"","");
		//write query to select row where otp obtained to client matches with db data
		try {
			user = userRepo.findByOtp(otp);
		} catch (Exception e) {
			logger.error(e);
			throw new FarmeasyException("Error in verifying otp!", e);
		}
		
		//if(user.getOtp().equals(otp))
		if(user.isPresent())
		{
			if(user.get().getOtp().equals(otp))
			{
				response.setStatus(1);
				response.setMessage("OTP verified successfully");
			}
		}
		
		return response;
	}

	public Response setnewpasswd(Userdto userdto) {

		//logger.debug(userdto.getPassword());
				response = new Response(0, "Password reset failed");
				int isupdatesuccessful = 0;
				//we are going to check this password in the db later on
				//as of now we'll test it on dummy stuff
//				User user = new User("abc","pabc",null);
				try {
//					userdto.setUsername("s@n.com");
//					userdto.setPassword("aaa");
					logger.debug(userdto.getPassword());
					isupdatesuccessful=userRepo.resetpswd(userdto.getUsername(),passwordEncoder.encode(userdto.getPassword()));
					//isupdatesuccessful++;
					//logger.debug(isupdatesuccessful);
				} catch (Exception e) {
					//log errors to the file
					logger.error(e);
					throw new FarmeasyException("Error in setting new password!", e);
				}
				
				//user = userRepo.findById(id);
				
//				if(user.getPassword().equals(pswrd))
				//if(user.get().getEmail().equals(id) && user.get().getPassword().equals(pswd))
				if (isupdatesuccessful!=0)
				{
					response.setStatus(1);
					response.setMessage("Password reset successfully");
				}
				
				return response;
	}

}
