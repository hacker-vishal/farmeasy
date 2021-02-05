package project.farmease.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.farmease.dto.Response;
import project.farmease.dto.UserProfile;
import project.farmease.pojo.User;
import project.farmease.service.EditProfileService;

@RequestMapping("/update")
@CrossOrigin
@RestController
public class EditProfileController {
	
	Logger logger = LogManager.getLogger(EditProfileController.class);
	
	@Autowired
	private EditProfileService editProfileService;

	@PostMapping("/")
	public Response doeditprofile(@RequestBody UserProfile userProfile) {
		
		return editProfileService.doEditProfile(userProfile);
	}
	
	@GetMapping("/details")
	public Optional<User> getprofile(@RequestParam String username) {
		
		return editProfileService.getprofile(username);
	}

}
