package project.farmease.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.models.Logincreds;
import project.farmease.models.Response;
import project.farmease.repositories.LogincredsRepo;

@CrossOrigin(origins = "*")
@RestController
public class Login {
	Logger logger = LogManager.getLogger(Login.class);
	private LogincredsRepo logincredsRepo;
	private Response response;
	private Optional<Logincreds> logincreds;
	
	@Autowired
	public void loginwired(LogincredsRepo logincredsRepo)
	{
		//log.debug("logincredsrepo autowired");
		this.logincredsRepo = logincredsRepo;
	}

	@GetMapping("/login")
	public Response dologin(@RequestParam("id") String id, @RequestParam("pswd") String pswd) {
		// TODO Auto-generated method stub
		response = new Response(0,"Check id and password!");
		
		//assume checked with db records
//		Logincreds logincreds = new Logincreds("abc", "pabc", null);
		
		logincreds = logincredsRepo.findById(id);
		
//		if (logincreds.getEmail().equals(id) && logincreds.getPassword().equals(pswd)) {
		if (logincreds.get().getEmail().equals(id) && logincreds.get().getPassword().equals(pswd)) {
			response.setStatus(1);
			response.setMessage("Logged in successfully!");
		}		
		return response;
	}	
}
