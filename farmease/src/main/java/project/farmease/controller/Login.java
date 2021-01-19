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

import project.farmease.dao.LogincredsRepo;
import project.farmease.dto.Response;
import project.farmease.dto.Userdto;
import project.farmease.pojo.Logincreds;

@CrossOrigin(origins="*")
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

	@PostMapping("/login")
	public Response dologin(@RequestBody Userdto userdto) {
		// TODO Auto-generated method stub
		response = new Response(0,"Check id and password!");
		
		//assume checked with db records
//		Logincreds logincreds = new Logincreds("abc", "pabc", null);
		
		logincreds = logincredsRepo.findById(userdto.getEmail());
		
//		if (logincreds.getEmail().equals(userdto.getId()) && logincreds.getPassword().equals(userdto.getPassword())) {
		if (logincreds.get().getEmail().equals(userdto.getEmail()) && logincreds.get().getPassword().equals(userdto.getPassword())) {
			response.setStatus(1);
			response.setMessage("Logged in successfully!");
		}		
		return response;
	}	
}
