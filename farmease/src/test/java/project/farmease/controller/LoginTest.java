package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.farmease.dto.Userdto;
import project.farmease.pojo.Response;

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
		Userdto userdto = new Userdto();
		userdto.setEmail("abc");
		userdto.setPassword("pabc");
		response = login.dologin(userdto);
		assertEquals(1, response.getStatus());
	}
	
	@Test
	void testlogincredsfailcase() {
		Userdto userdto = new Userdto();
		userdto.setEmail("ab");
		userdto.setPassword("pab");
		response = login.dologin(userdto);
		assertEquals(0, response.getStatus());
	}
}
