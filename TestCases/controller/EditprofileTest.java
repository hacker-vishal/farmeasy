package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.farmease.models.Address;
import project.farmease.models.Response;
import project.farmease.models.User;
import project.farmease.models.UserProfile;

class EditprofileTest {

	Logger logger = LogManager.getLogger(EditprofileTest.class);
	private User user;
	private Address address;
	private static Editprofile editprofile;
	private Response response;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		editprofile = new Editprofile();
	}

	@Test
	void testprofileupdation() {
		user = new User("abc", "a", "b", "7847984", "pabc");
		address = new Address("abc", "loc", "pune", "ms", 434432);
		UserProfile userProfile = new UserProfile(user.getEmail(), user.getFname(), user.getLname(), user.getMobileno(), 
				address.getLocation(), address.getCity(), address.getState(), address.getZipcode());
		response = editprofile.doeditprofile(userProfile);
		assertEquals(1, response.getStatus());
	}
}
