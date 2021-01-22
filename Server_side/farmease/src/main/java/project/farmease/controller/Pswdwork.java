package project.farmease.controller;

import java.util.Optional;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.pojo.User;

@CrossOrigin(origins = "*")
@RestController
public class Pswdwork {
	Logger logger = LogManager.getLogger(Pswdwork.class);
	private Optional<User> user;
	private Response response;
	
	@Autowired
    private UserRepo userRepo;
	
	@GetMapping("/pswdreset")
//	public Optional<User> passwdreset(String id) {
	public Response passwdreset(String id) {
		//id will be checked with entries in db for match
//		user = new User("abc","",0);
//		try {
//			user = userRepo.findById(id);
//		} catch (Exception e) {
//			logger.debug(e.getMessage(),e);
//		}
		
		response = new Response(0, "User not found");
		
//		if(logincreds.getEmail().equals(id)) 
		if(user.isPresent() && user.get().getEmail().equals(id))
		{
			//logger.debug("got in");
			//6 digit random number generation in java
			int random = new Random().nextInt(900000) + 100000;
			//logger.debug(random);
			
			//create trigger to set otp to null
			//db logic to add random number into table
//			userRepo.createotp(random,id);
			
			//return the response
			response.setStatus(1);
			response.setMessage("User found");
		}
		return response;
	}

	@GetMapping("/")
	public Response checkotp(int otp) { 
		response = new Response(0, "OTP verification failed");
		
		//assume it came from db
//		Logincreds logincreds = new Logincreds("abc","",123456);
		//write query to select row where otp obtained to client matches with db data
//		logincreds = logincredsRepo.checkotp(otp);
		
		//if(logincreds.getOtp().equals(otp))
		if(user.get().getOtp().equals(otp))
		{
			response.setStatus(1);
			response.setMessage("OTP verified successfully");
		}
		return response;
	}

	@PutMapping("/pswdreset")
	public Response setnewpasswd(String id, String pswd) {
		
		response = new Response(0, "Password reset failed");
		int isupdatesuccessful = 0;
		//we are going to check this password in the db later on
		//as of now we'll test it on dummy stuff
//		User user = new User("abc","pabc",null);
//		try {
//			isupdatesuccessful = userRepo.resetpswd(id, pswd);
//			//isupdatesuccessful++;
//			//logger.debug(isupdatesuccessful);
//		} catch (Exception e) {
//			//log errors to the file
//			logger.debug(e.getMessage(),e);
//		}
		
		//user = userRepo.findById(id);
		
//		if(user.getPassword().equals(pswrd))
		//if(user.get().getEmail().equals(id) && user.get().getPassword().equals(pswd))
		if (isupdatesuccessful!=0)
		{
			response.setStatus(1);
			response.setMessage("Password reset successfully");
		}
		
		return response;
	}
}