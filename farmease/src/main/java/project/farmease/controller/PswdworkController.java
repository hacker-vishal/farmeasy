package project.farmease.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.farmease.dto.Response;
import project.farmease.dto.Userdto;
import project.farmease.service.PswdworkService;

@RestController
@RequestMapping("/password")
public class PswdworkController {
	Logger logger = LogManager.getLogger(PswdworkController.class);
	
	@Autowired
	private PswdworkService pswdworkService;
	
	@CrossOrigin
	@GetMapping("/pswdreset")
//	public Optional<User> passwdreset(String id) {
	public Response passwdreset(String id) {
		return pswdworkService.passwdreset(id);
	}
	
	@CrossOrigin
	@GetMapping("/getotp")
	public Integer getotp(String id) 
	{
		return pswdworkService.getotp(id);
	}

	@CrossOrigin
	@GetMapping("/checkotp")
	public Response checkotp(int otp) { 
		return pswdworkService.checkotp(otp);
	}

	@CrossOrigin
	@PostMapping("/setnewpass")
	public Response setnewpasswd(@RequestBody Userdto userdto) {
		return pswdworkService.setnewpasswd(userdto);
	}
}