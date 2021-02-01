package project.farmease.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.farmease.dao.UserRepo;
import project.farmease.dao.WishlistRepo;
import project.farmease.dto.Response;
import project.farmease.pojo.Wishlist;

@Transactional
@Service
public class WishlistService {
	
	Logger logger = LogManager.getLogger(WishlistService.class);
	
	@Autowired
	private WishlistRepo wishlistRepo;
	@Autowired
	private UserRepo userRepo;
	
	public Response addtowishlist(Wishlist wishlist) {

		Response response = new Response(0,"Insertion to wishlist failed!!!");
		
		Boolean isPresent = false;
		
		try {
			isPresent = userRepo.existsById(wishlist.getEmail());
		} catch (Exception e1) {
			logger.error(e1);
		}
		
		if(wishlist.getEmail()!=null && isPresent==true)
		{
			//insert into database
			try {
				wishlistRepo.save(wishlist);
				response.setStatus(1);
				response.setMessage("Added to wishlist");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}			
		}
		else
		{
			response.setMessage("You must signup to avail these services");
		}
		
		return response;
	}
	
	public List<Wishlist> getwishlist(String email) {

		//assume this dummy data as wishlisted items in database
//		Wishlist w1 = new Wishlist("v@g.com", "tractor", "farmtrac", "cultivating", "s@n.com", "pune", 2000);
//		Wishlist w2 = new Wishlist("v@g.com", "tractor", "farmtrac", "cultivating", "s@n.com", "pune", 2000);
//		Wishlist w3 = new Wishlist("v@g.com", "tractor", "farmtrac", "cultivating", "s@n.com", "pune", 2000);
		
		List<Wishlist> l = new ArrayList<Wishlist>();
		
		List<Wishlist> getlist = new ArrayList<Wishlist>();
		
//		l.add(w1);
//		l.add(w2);
//		l.add(w3);
		
		l = wishlistRepo.findByEmail(email);
		
		for(Wishlist w:l)
		{
			if(w.getEmail().equalsIgnoreCase(email))
			{
				getlist.add(w);
			}
		}
		
		if(getlist.size()==l.size())
		{
			return getlist;
		}
		
		return new ArrayList<Wishlist>();
	}
}
