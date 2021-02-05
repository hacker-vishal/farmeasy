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
import project.farmease.pojo.Wishlist;
import project.farmease.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	Logger logger = LogManager.getLogger(WishlistController.class);
	
	@Autowired
	private WishlistService wishlistService;

	@CrossOrigin
	@PostMapping("/add")
	public Response addToWishlist(@RequestBody Wishlist wishlist) {
		
		return wishlistService.addtowishlist(wishlist);
	}

	@CrossOrigin
	@GetMapping("/")
	public List<Wishlist> getWishlist(@RequestParam String email) {
		
		return wishlistService.getwishlist(email);
	}
	
	@CrossOrigin
	@PostMapping("/remove")
	public Response removeFromWishlist(@RequestBody Wishlist wishlist)
	{
		return wishlistService.removeFromWishlist(wishlist);
	}
}
