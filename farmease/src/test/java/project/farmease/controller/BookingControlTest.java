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
	private static BookingController bookingControl;
	private Response response;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bookingControl = new BookingController();
	}

	@Test
	void bookingavailabilitytest() {
		String dob = "2021-03-07 09:01:15";
		String dof = "2021-03-08 10:01:15";
		Booking booking = new Booking(1, "s@n.com", "vj.com", "tractor", "swaraj", "pune", "cultivating",Timestamp.valueOf(dob),Timestamp.valueOf(dof), 1000d, false);
		//logger.debug(Timestamp.valueOf(dob));
		response=bookingControl.checkavailability(booking);
		assertEquals(1, response.getStatus());
	}
	
	
	@Test
	void bookingavailabilitytestfailcase() {
		String dob = "2021-03-03 10:01:15";
		String dof = "2021-03-05 10:01:15";
		Booking booking = new Booking(1, "s@n.com", "vj.com", "tractor", "swaraj", "pune", "cultivating",Timestamp.valueOf(dob),Timestamp.valueOf(dof), 1000d, false);
		response=bookingControl.checkavailability(booking);
		assertEquals(0, response.getStatus());
	}
	
	@Test
	void dobookingtest()
	{
		String dob = "2021-03-03 10:01:15";
		String dof = "2021-03-05 10:01:15";
		Booking booking = new Booking(1, "s@n.com", "vj.com", "tractor", "swaraj", "pune", "cultivating",Timestamp.valueOf(dob),Timestamp.valueOf(dof), 1000d, false);
        response = bookingControl.dobooking(booking);
        assertEquals(1, response.getStatus());
	}

}
