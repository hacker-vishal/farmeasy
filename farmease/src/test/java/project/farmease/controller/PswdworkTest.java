package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.farmease.dto.Response;
import project.farmease.pojo.Logincreds;

class PswdworkTest {
	Logger log = LogManager.getLogger(PswdworkTest.class);
	private static Logincreds logincreds;
	private static Pswdwork pswdwork;
	private Response response;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pswdwork = new Pswdwork();
	}

//	@Test
//	void testuserexists() {
//		logincreds = new Logincreds("abc","",0);
//		response = pswdwork.passwdreset(logincreds.getEmail());
//		assertEquals(1, response.getStatus());
//	}
//	
//	@Test
//	void testuserexistsfailcase() {
//		logincreds = new Logincreds("xyz","",0);
//		response = pswdwork.passwdreset(logincreds.getEmail());
//		assertEquals(0, response.getStatus());
//	}
//	
//	@Test
//	void testcheckotp()
//	{
//		int otp = 123456;
//		response = pswdwork.checkotp(otp);
//		assertEquals(1, response.getStatus());
//	}
//	
//	@Test
//	void testcheckotpfailcase()
//	{
//		int otp = 123321;
//		response = pswdwork.checkotp(otp);
//		assertEquals(0, response.getStatus());
//	}
//	
//	@Test
//	void testsetnewpasswd()
//	{
//		String id = "abc";
//		String pswd = "pabc";
//		response = pswdwork.setnewpasswd(id, pswd);
//		assertEquals(1, response.getStatus());
//	}
//	
//	@Test
//	void testsetnewpasswdfailcase()
//	{
//		String id = "abc";
//		String pswd = "pppp";
//		response = pswdwork.setnewpasswd(id, pswd);
//		assertEquals(0, response.getStatus());
//	}
}
