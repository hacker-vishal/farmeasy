package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.farmease.dto.Response;
import project.farmease.dto.Userdto;
import project.farmease.pojo.User;

class PswdworkTest {
	Logger log = LogManager.getLogger(PswdworkTest.class);
	private static Userdto userdto;
	private static Pswdwork pswdwork;
	private Response response;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pswdwork = new Pswdwork();
	}

	@Test
	void testuserexists() {
		userdto = new Userdto("abc","");
		response = pswdwork.passwdreset(userdto.getUsername());
		assertEquals(1, response.getStatus());
	}
	
	@Test
	void testuserexistsfailcase() {
		userdto = new Userdto("xyz","");
		response = pswdwork.passwdreset(userdto.getUsername());
		assertEquals(0, response.getStatus());
	}
	
	@Test
	void testcheckotp()
	{
		int otp = 123456;
		response = pswdwork.checkotp(otp);
		assertEquals(1, response.getStatus());
	}
	
	@Test
	void testcheckotpfailcase()
	{
		int otp = 123321;
		response = pswdwork.checkotp(otp);
		assertEquals(0, response.getStatus());
	}
	
	@Test
	void testsetnewpasswd()
	{
		userdto = new Userdto("xyz","");
		response = pswdwork.setnewpasswd(userdto);
		assertEquals(1, response.getStatus());
	}
	
	@Test
	void testsetnewpasswdfailcase()
	{
		userdto = new Userdto("xyz","");
		response = pswdwork.setnewpasswd(userdto);
		assertEquals(0, response.getStatus());
	}
}
