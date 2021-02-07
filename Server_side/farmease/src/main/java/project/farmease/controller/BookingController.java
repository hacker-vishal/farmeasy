package project.farmease.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.farmease.dto.Response;
import project.farmease.pojo.Booking;
import project.farmease.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	Logger logger = LogManager.getLogger(BookingController.class);
	
	@Autowired
	private BookingService bookingService;
	
	@CrossOrigin//("http://localhost:4200")
	@PostMapping("/checkavail")
	public Response checkavailability(@RequestBody Booking booking) {
	
		logger.debug(booking.getServiceprovider()+" "+booking.getDatefinish()+" "+booking.getDateofbooking());
		 
		return bookingService.checkavailability(booking);
	}


	@CrossOrigin
	@PostMapping("/bookit")
	public Response dobooking(@RequestBody Booking booking) {
		
		logger.debug(booking.getServiceprovider());
		
		return bookingService.dobooking(booking);
	}
	
	@CrossOrigin
	@GetMapping("/get")
	public List<Booking> getmybookings(@RequestParam String username)
	{
		logger.debug(username);
		return bookingService.getbookings(username);
	}
	
	@CrossOrigin
	@GetMapping("/cancelbooking")
	public Response cancelmybookings(@RequestParam Integer bid)
	{
		logger.debug(bid);
		return bookingService.cancelbooking(bid);
	}
	
	@CrossOrigin
	@GetMapping("/getslots")
	public List<Booking> getslots(@RequestParam String sp)
	{
		logger.debug(sp);
		return bookingService.getAlreadyBookedSlots(sp);
	}
}
