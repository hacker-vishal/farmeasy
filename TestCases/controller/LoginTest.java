package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.farmease.models.Response;

class LoginTest {
	
//	Logger logger = LogManager.getLogger(LoginTest.class);
	private static Login login;
	private Response response;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		login = new Login();
	}

	@Test
	void testlogincreds() {
		response = login.dologin("abc","pabc");
		assertEquals(1, response.getStatus());
	}
	
	@Test
	void testlogincredsfailcase() {
		response = login.dologin("ab","pass");
		assertEquals(0, response.getStatus());
	}
}
