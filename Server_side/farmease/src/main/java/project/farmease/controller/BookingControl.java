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
import project.farmease.dao.HostuserRepo;
import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.pojo.Booking;
import project.farmease.pojo.User;

@RestController
@RequestMapping("/booking")
public class BookingControl {
	
	Logger logger = LogManager.getLogger(BookingControl.class);
	@Autowired
	private BookingRepo bookingRepo;
	@Autowired
	private HostuserRepo hostuserRepo;
	@Autowired
	private UserRepo userRepo;
	
	List<Booking> l;
	
	
	@CrossOrigin//("http://localhost:4200")
	@PostMapping("/checkavail")
	public Response checkavailability(@RequestBody Booking booking) {
	
		logger.debug(booking.getServiceprovider()+" "+booking.getDatefinish()+" "+booking.getDateofbooking());
		
		Response resp = new Response(0,"booking cannot be done in this slot!!!");
		int c=0;
		int x=0;
		
		//assume db dummy booking data
//		Booking book1 = new Booking(1, "v@j.com", "s@n.com", "tractor", "Mahindra", "pune", "cultivating", Timestamp.valueOf("2021-03-01 09:01:15"),Timestamp.valueOf("2021-03-02 09:01:15"), 1000d, true);
//		Booking book2 = new Booking(2, "v@g.com", "s@n.com", "tractor", "farmtrac", "blr", "ploughing", Timestamp.valueOf("2021-03-03 09:01:15"),Timestamp.valueOf("2021-03-04 09:01:15"), 1000d, true);
//		Booking book3 = new Booking(3, "v@j.com", "s@n.com", "tractor", "farmtrac", "blr", "harvesting", Timestamp.valueOf("2021-03-04 09:02:15"),Timestamp.valueOf("2021-03-05 09:01:15"), 1000d, true);
		
		 l = new ArrayList<Booking>();
		
//		l.add(book1);
//		l.add(book2);
//		l.add(book3);
		 
		if(hostuserRepo.existsByHostemail(booking.getServiceprovider()))
		{	
			try {
				l= bookingRepo.findByServiceprovider(booking.getServiceprovider());
				logger.debug(l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
			
			
			//logger.debug(l.size());
			
			if(l.size()!=0 && l!=null)
			{
			for(Booking b:l)
			{
				if((booking.getDateofbooking().before(b.getDateofbooking()) || booking.getDateofbooking().after(b.getDatefinish()))) 
				{  
					logger.debug("1st condition");
					for(Booking book:l) 
					{
						if((booking.getDatefinish().before(book.getDateofbooking()) || booking.getDatefinish().after(book.getDatefinish())))
						{
							logger.debug("2nd condition");
							c++;
							logger.debug(c);
						}
						if(c==l.size())
						{
							if(!(booking.getDateofbooking().before(b.getDateofbooking()) && booking.getDatefinish().after(b.getDatefinish())))
							{
								logger.debug("3rd condition");
								x++;
								c=0;
							}
						}
					}
				}
			}
			
			
			//logger.debug(c);
			
			if (x == l.size()) 
			{
				resp.setStatus(1);
				resp.setMessage("You can book now!");
			}
			}
			else
			{
				resp.setStatus(1);
				resp.setMessage("You can book now!");
			}
//			
//			logger.debug(resp.getStatus());
		}
		else
		{
			resp.setMessage("Serviceprovider does not exist!");
		}
		
		return resp;
	}


	@CrossOrigin
	@PostMapping("/bookit")
	public Response dobooking(@RequestBody Booking booking) {

		Response response = new Response(0, "Booking failed");
		
		Boolean isUserPresent = false;
		logger.debug(booking.getEmail());
		
		try {
			if(booking.getEmail() != null)
			isUserPresent = userRepo.existsById(booking.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		
		logger.debug(isUserPresent);
		
		if(!isUserPresent)
		{
			response.setMessage("You need to signup first!!!");
		}
		else
		{
			bookingRepo.save(booking);
			response.setStatus(1);
			response.setMessage("Booking successful!");
		}
		
		return response;
	}
}
