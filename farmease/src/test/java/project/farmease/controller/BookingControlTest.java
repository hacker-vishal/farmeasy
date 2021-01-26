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

	@Test
	void bookingavailabilitytest() {
		//Booking booking = new Booking(1, "s@n.com", "vj.com", "tractor", "swaraj", "cultivating",Timestamp.valueOf(""),Timestamp.valueOf(""), 1000d);
		String dob = "2021-03-01";
		String dof = "2000-03-04";
		response=bookingControl.checkavailability("vj.com",Timestamp.valueOf(dob),Timestamp.valueOf(dof));
		assertEquals(1, response.getStatus());
	}

}
