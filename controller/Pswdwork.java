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

import project.farmease.models.Logincreds;
import project.farmease.models.Response;
import project.farmease.repositories.LogincredsRepo;

@CrossOrigin(origins = "*")
@RestController
public class Pswdwork {
	Logger logger = LogManager.getLogger(Pswdwork.class);
	private LogincredsRepo logincredsRepo;
	private Optional<Logincreds> logincreds;
	private Response response;
	
	@Autowired
	public void logincredwired(LogincredsRepo logincredsRepo)
	{
		//logger.debug("LogincredsRepo autowired");
		this.logincredsRepo = logincredsRepo;
	}
	
	@GetMapping("/pswdreset")
//	public Optional<Logincreds> passwdreset(String id) {
	public Response passwdreset(String id) {
		//id will be checked with entries in db for match
//		logincreds = new Logincreds("abc","",0);
		try {
			logincreds = logincredsRepo.findById(id);
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		
		response = new Response(0, "User not found");
		
//		if(logincreds.getEmail().equals(id)) 
		if(logincreds.isPresent() && logincreds.get().getEmail().equals(id))
		{
			//logger.debug("got in");
			//6 digit random number generation in java
			int random = new Random().nextInt(900000) + 100000;
			//logger.debug(random);
			
			//create trigger to set otp to null
			//db logic to add random number into table
			logincredsRepo.createotp(random,id);
			
			//return the response
			response.setStatus(1);
			response.setMessage("User found");
		}
//		return logincreds;
		return response;
	}

	@GetMapping("/")
	public Response checkotp(int otp) { 
		response = new Response(0, "OTP verification failed");
		
		//assume it came from db
//		Logincreds logincreds = new Logincreds("abc","",123456);
		//write query to select row where otp obtained to client matches with db data
		logincreds = logincredsRepo.checkotp(otp);
		
		//if(logincreds.getOtp().equals(otp))
		if(logincreds.get().getOtp().equals(otp))
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
//		Logincreds logincreds = new Logincreds("abc","pabc",null);
		try {
			isupdatesuccessful = logincredsRepo.resetpswd(id, pswd);
			//isupdatesuccessful++;
			//logger.debug(isupdatesuccessful);
		} catch (Exception e) {
			//log errors to the file
			logger.debug(e.getMessage(),e);
		}
		
		//logincreds = logincredsRepo.findById(id);
		
//		if(logincreds.getPassword().equals(pswrd))
		//if(logincreds.get().getEmail().equals(id) && logincreds.get().getPassword().equals(pswd))
		if (isupdatesuccessful!=0)
		{
			response.setStatus(1);
			response.setMessage("Password reset successfully");
		}
		
		return response;
	}
}