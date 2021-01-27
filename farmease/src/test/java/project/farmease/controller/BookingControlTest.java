package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.farmease.dto.Response;
import project.farmease.pojo.Booking;
import project.farmease.pojo.Hostuser;

class BookingControlTest {
	
	Logger logger = LogManager.getLogger(BookingControlTest.class);
	private static BookingControl bookingControl;
	private Response response;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bookingControl = new BookingControl();
	}

//	@Test
//	void bookingavailabilitytest() {
//		//Booking booking = new Booking(1, "s@n.com", "vj.com", "tractor", "swaraj", "cultivating",Timestamp.valueOf(""),Timestamp.valueOf(""), 1000d);
//		String dob = "2021-03-07 09:01:15";
//		String dof = "2021-03-08 10:01:15";
//		response=bookingControl.checkavailability("a@b.com",Timestamp.valueOf(dob),Timestamp.valueOf(dof));
//		assertEquals(1, response.getStatus());
//	}
//	
//	
//	@Test
//	void bookingavailabilitytestfailcase() {
//		//Booking booking = new Booking(1, "s@n.com", "vj.com", "tractor", "swaraj", "cultivating",Timestamp.valueOf(""),Timestamp.valueOf(""), 1000d);
//		String dob = "2021-03-03 10:01:15";
//		String dof = "2021-03-05 10:01:15";
//		response=bookingControl.checkavailability("a@b.com",Timestamp.valueOf(dob),Timestamp.valueOf(dof));
//		assertEquals(0, response.getStatus());
//	}

}
