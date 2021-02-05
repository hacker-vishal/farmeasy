package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.farmease.dto.Response;
import project.farmease.dto.UserProfile;
import project.farmease.pojo.User;

class EditprofileTest {

	Logger logger = LogManager.getLogger(EditprofileTest.class);
	private User user;
	private static EditProfileController editprofile;
	private Response response;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		editprofile = new EditProfileController();
	}

	@Test
	void testprofileupdation() {
		user = new User("abc", "a", "b", "7847984", "pabc",Instant.now(),false,0);
		UserProfile userProfile = new UserProfile(user.getEmail(), user.getFname(), user.getLname(), user.getMobileno());
		response = editprofile.doeditprofile(userProfile);
		assertEquals(1, response.getStatus());
	}
}
