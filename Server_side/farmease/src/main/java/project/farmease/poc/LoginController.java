package project.farmease.poc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.farmease.dto.Response;
import project.farmease.dto.Userdto;

@CrossOrigin(origins="*")
@RestController
public class LoginController {
	Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private LoginServicePoc loginService;

	@PostMapping("/login")
	public Response dologin(@RequestBody Userdto userdto) {
		
		return loginService.dologin(userdto);
	}	
}
