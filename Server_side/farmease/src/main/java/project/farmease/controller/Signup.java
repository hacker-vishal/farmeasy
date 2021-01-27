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

import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.dto.Userdto;
import project.farmease.pojo.User;

@CrossOrigin(origins = "*")
@RestController
public class Signup {
	
	Logger logger = LogManager.getLogger(Signup.class);
	@Autowired
	private UserRepo userRepo;

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
			//e.printStackTrace();
			logger.debug(e.getMessage(),e);
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
