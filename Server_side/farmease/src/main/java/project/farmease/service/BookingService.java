package project.farmease.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.dao.BookingRepo;
import project.farmease.dao.HostuserRepo;
import project.farmease.dao.UserRepo;
import project.farmease.dto.Response;
import project.farmease.farmeasyexception.FarmeasyException;
import project.farmease.pojo.Booking;

@Transactional
@Service
public class BookingService {
	
	Logger logger = LogManager.getLogger(BookingService.class);
	
	@Autowired
	HostuserRepo hostuserRepo;
	@Autowired
	BookingRepo bookingRepo;
	@Autowired
	private UserRepo userRepo;
	
	public Response checkavailability(Booking booking)
	{
		Response resp = new Response(0,"booking cannot be done in this slot!!!");
		int c=0;
		int x=0;
		
		//assume db dummy booking data
//		Booking book1 = new Booking(1, "v@j.com", "s@n.com", "tractor", "Mahindra", "pune", "cultivating", Timestamp.valueOf("2021-03-01 09:01:15"),Timestamp.valueOf("2021-03-02 09:01:15"), 1000d, true);
//		Booking book2 = new Booking(2, "v@g.com", "s@n.com", "tractor", "farmtrac", "blr", "ploughing", Timestamp.valueOf("2021-03-03 09:01:15"),Timestamp.valueOf("2021-03-04 09:01:15"), 1000d, true);
//		Booking book3 = new Booking(3, "v@j.com", "s@n.com", "tractor", "farmtrac", "blr", "harvesting", Timestamp.valueOf("2021-03-04 09:02:15"),Timestamp.valueOf("2021-03-05 09:01:15"), 1000d, true);
		
		 List<Booking> l = new ArrayList<Booking>();
		 //List<Booking> d = new ArrayList<Booking>();
		
//		l.add(book1);
//		l.add(book2);
//		l.add(book3);
		 
		if(hostuserRepo.existsByHostemail(booking.getServiceprovider()))
		{	
			try {
				bookingRepo.updateinvalidmark();
				l= bookingRepo.findByServiceprovider(booking.getServiceprovider());
				logger.debug(l);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e);
				throw new FarmeasyException("Error in checking availability for booking!", e);
			}
			
			//logger.debug(l.size());
			

//			say we have 3 bookings already done on some service provider viz. b1,b2,b3
//			user sent booking===  3-2,6-2
//			b1===1-2,5-2 -->
//			b2===8-2,10-2
//			b3===15-2,3-3
//		
			
			if(l.size()!=0 && l!=null)
			{
			for(Booking b:l)
			{
				if((!(  (booking.getEquipmenttype().equalsIgnoreCase(b.getEquipmenttype())) && (booking.getLocation().equalsIgnoreCase(b.getLocation()))
						   && (booking.getManufacturer().equalsIgnoreCase(b.getManufacturer())) && (booking.getServiceprovider().equalsIgnoreCase(b.getServiceprovider()))	
						   && (booking.getServicetype().equalsIgnoreCase(b.getServicetype()))   )))
				{
					logger.debug("ok not the same type");
					resp.setStatus(1);
					resp.setMessage("You can book now!");
				}
				
				else {
					logger.debug("its of same type check for dates!!!");
				if( (booking.getDateofbooking().before(b.getDateofbooking()) || booking.getDateofbooking().after(b.getDatefinish())) ) 
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
				else
				break;
			
				
			//logger.debug(c);
			
				if (x == l.size()) 
				{
					
					resp.setStatus(1);
					resp.setMessage("You can book now!");
					logger.debug(resp.getStatus()+" "+resp.getMessage());
					return resp;
				}
			}
			}
			}
			else
			{
				resp.setStatus(1);
				resp.setMessage("You can book now!");
				return resp;
			}
			
//			logger.debug(resp.getStatus());
		}
		else
		{
			resp.setMessage("Serviceprovider does not exist!");
			return resp;
		}
		
		return resp;
	}
	
	public Response dobooking(Booking booking)
	{
        Response response = new Response(0, "Booking failed");
		
		Boolean isUserPresent = false;
		logger.debug(booking.getEmail());
		
		try {
			if(booking.getEmail() != null)
			isUserPresent = userRepo.existsById(booking.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new FarmeasyException("Error in doing book operation!", e);
		}
		
		logger.debug(isUserPresent);
		
		if(!isUserPresent)
		{
			response.setMessage("You need to signup first!!!");
		}
		else
		{
			logger.debug(booking.getDateofbooking()+" "+booking.getDatefinish()+" "+booking.getRent()+" "+booking.getInvalid());
			try {
				bookingRepo.save(booking);
			} catch (Exception e) {
				logger.error(e);
				throw new FarmeasyException("Error in doing booking!", e);
			}
			response.setStatus(1);
			response.setMessage("Booking successful!");
		}
		
		return response;
	}

	public List<Booking> getbookings(String username) {
		// TODO Auto-generated method stub
		
		List<Booking> l = new ArrayList<Booking>();
		
		try {
			l= bookingRepo.findByEmail(username);
		} catch (Exception e) {
			logger.error(e);
			throw new FarmeasyException("Error in getting bookings!", e);
		}
		logger.debug(l);
		
		return l;
	}

	public Response cancelbooking(Integer bid) {

		logger.debug(bid);
		Response response = new Response(0, "Booking cancellation failed!");
		
		try {
			if(bid!=null)
			{
				bookingRepo.deleteById(bid);
				response.setStatus(1);
				response.setMessage("Booking has been cancelled successfully! If you've any issues, reach us at help@farmeasy.com");
			}
		} catch (Exception e) {
			logger.error(e);
			throw new FarmeasyException("Error in cancelling booking!", e);
		}
		return response;
	}

	public List<Booking> getAlreadyBookedSlots(String sp) {
		List<Booking> l = new ArrayList<Booking>();
		
		try {
			if(sp!=null)
			l = bookingRepo.findByServiceprovider(sp);
		} catch (Exception e) {
			logger.error(e);
			throw new FarmeasyException("Error in fetching booked slots!", e);
		}
				
		return l;
	}
}
