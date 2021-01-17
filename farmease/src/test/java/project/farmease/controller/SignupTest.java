package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.farmease.pojo.Response;
import project.farmease.pojo.User;

class SignupTest {
	
	Logger log = LogManager.getLogger(SignupTest.class);
	private static User user;
	private static Signup signup;
	private Response response;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		signup = new Signup();
	}

	@Test
	void testsignup() {
		user = new User("jklm", "a", "b", "23232","");
		response = signup.dosignup(user);
		assertEquals(0, response.getStatus());
	}

	@Test
	void testsignupfailcase() {
		user = new User("abc", "a", "b", "23232","");
		response = signup.dosignup(user);
		assertEquals(1, response.getStatus());
	}
}
