package project.farmease.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.dto.Userdto;
import project.farmease.pojo.User;

@CrossOrigin(origins="*")
@RestController
public class Login {
	Logger logger = LogManager.getLogger(Login.class);
	private Response response;
	private Optional<User> user;
	
	@Autowired
	private UserRepo userRepo;

	@PostMapping("/login")
	public Response dologin(@RequestBody Userdto userdto) {
		// TODO Auto-generated method stub
		response = new Response(0,"Check id and password!");
		
		//assume checked with db records
//		User user = new User("abc", "pabc", null);
		
		user = userRepo.findById(userdto.getUsername());
		
//		if (user.getEmail().equals(userdto.getUsername()) && user.getPassword().equals(userdto.getPassword())) {
		if (user.get().getEmail().equals(userdto.getUsername()) && user.get().getPassword().equals(userdto.getPassword())) {
			response.setStatus(1);
			response.setMessage("Logged in successfully!");
		}		
		return response;
	}	
}
