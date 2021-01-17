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

import project.farmease.dao.LogincredsRepo;
import project.farmease.dao.UserRepo;
import project.farmease.dto.Userdto;
import project.farmease.pojo.Logincreds;
import project.farmease.pojo.Response;
import project.farmease.pojo.User;

@CrossOrigin(origins = "*")
@RestController
public class Signup {
	
	Logger logger = LogManager.getLogger(Signup.class);
	private UserRepo userRepo;
	
	@Autowired
	public void hostuserwired(UserRepo userRepo, LogincredsRepo logincredsRepo)
	{
		//logger.debug("UserRepo autowired");
		this.userRepo = userRepo;
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
		//did create some trigger for inserting into logincreds
		//return user1;
	    return response;
	}
}
