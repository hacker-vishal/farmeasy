package project.farmease.poc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.farmease.dto.Response;
import project.farmease.pojo.User;

@CrossOrigin(origins = "*")
@RestController
public class SignupController {
	
	Logger logger = LogManager.getLogger(SignupController.class);
	
	@Autowired
	private SignupServicePoc signupService;

    @PutMapping("/signup")
	public Response dosignup(@RequestBody User user) {
	
    	return signupService.dosignup(user);
	}
}
