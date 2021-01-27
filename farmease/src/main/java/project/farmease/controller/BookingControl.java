package project.farmease.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.farmease.dao.BookingRepo;
import project.farmease.dto.Response;
import project.farmease.pojo.Booking;
import project.farmease.pojo.User;

@RestController
@RequestMapping("/booking")
public class BookingControl {
	
	Logger logger = LogManager.getLogger(BookingControl.class);
	@Autowired
	private BookingRepo bookingRepo;
	List<Booking> l;
	
	
	@CrossOrigin//("http://localhost:4200")
	@PostMapping("/checkavail")
	public Response checkavailability(@RequestBody Booking booking) {
	
		Response resp = new Response(0,"booking not available on that day");
//		
//		
//		//assume db dummy booking data
//		Booking book1 = new Booking(1, "s@n.com", "a@b.com", "tractor", "Mahindra", "pune", "cultivating", Timestamp.valueOf("2021-03-01 09:01:15"),Timestamp.valueOf("2021-03-02 09:01:15"), 1000d, true);
//		Booking book2 = new Booking(2, "v@g.com", "a@b.com", "tractor", "farmtrac", "blr", "ploughing", Timestamp.valueOf("2021-03-03 09:01:15"),Timestamp.valueOf("2021-03-04 09:01:15"), 1000d, true);
//		Booking book3 = new Booking(3, "s@n.com", "a@b.com", "tractor", "farmtrac", "blr", "harvesting", Timestamp.valueOf("2021-03-04 09:02:15"),Timestamp.valueOf("2021-03-05 09:01:15"), 1000d, true);
		
		 l = new ArrayList<Booking>();
		
//		l.add(book1);
//		l.add(book2);
//		l.add(book3);
		
		l= bookingRepo.findbooking(booking.getEmail(),booking.getIsInvalid()); 
		
		int c=0;
		
		//logger.debug(l.size());
		
		for(Booking b:l)
		{
			
			if((booking.getDateofbooking().before(b.getDateofbooking()) || booking.getDateofbooking().after(b.getDatefinish()))    &&      
					(booking.getDatefinish().before(b.getDateofbooking()) || booking.getDatefinish().after(b.getDatefinish())))
			{
				c++;
				
			}
		}
		
		//logger.debug(c);
		
		if (c == l.size()) {
			resp.setStatus(1);
			resp.setMessage("Booking available!");
		}
//		
//		logger.debug(resp.getStatus());
		
		
	return resp;
}

}
