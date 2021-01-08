package project.farmease.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.models.Logincreds;
import project.farmease.models.Response;
import project.farmease.models.User;
import project.farmease.repositories.LogincredsRepo;
import project.farmease.repositories.UserRepo;

@CrossOrigin(origins = "*")
@RestController
public class Signup {
	
	Logger logger = LogManager.getLogger(Signup.class);
	private UserRepo userRepo;
	private LogincredsRepo logincredsRepo;
	
	@Autowired
	public void hostuserwired(UserRepo userRepo, LogincredsRepo logincredsRepo)
	{
		//logger.debug("UserRepo autowired");
		this.userRepo = userRepo;	
		//logger.debug("logincredsRepo autowired");
		this.logincredsRepo = logincredsRepo;
	}

//	@PostMapping(value = "/signup", consumes=MediaType.APPLICATION_JSON_VALUE)
//	@RequestMapping(method = RequestMethod.PUT, value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping("/signup")
	public Response dosignup(@RequestBody User user) {
	//public User dosignup() {
		//logger.debug(user.getEmail());
		Response response = new Response(1, "Account created!");
		boolean idexists=true;
		//assume this is in the database
//	    User u = new User("jklm", "a", "b", "23232","");
		
		//we check if id we are inserting is already present or not
//	    idexists = u.getEmail().equals(user.getEmail());
		
	    try {
			idexists = userRepo.existsById(user.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(idexists)
		{
			response.setStatus(0);
			response.setMessage("User already exist!");
		}
		else 
		{
			//actual db logic
			userRepo.save(user);
		}		
		//do create some trigger for inserting into logincreds
//		//entry in logincreds
//		Logincreds logincreds = new Logincreds(user.getEmail(), user.getPassword(), null);
//		logincredsRepo.save(logincreds);
		//return user1;
	    return response;
	}
}
