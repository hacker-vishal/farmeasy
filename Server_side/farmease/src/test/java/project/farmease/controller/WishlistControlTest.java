package project.farmease.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.farmease.dto.Response;
import project.farmease.pojo.User;
import project.farmease.pojo.Wishlist;

class WishlistControlTest {
	
	Response response = new Response();
	private static WishlistControl wishlistControl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		wishlistControl = new WishlistControl();
	}

	@Test
	void addtowishlisttest() {
		Wishlist wishlist = new Wishlist("v@g.com", "tractor", "farmtrac", "cultivating", "s@n.com", "pune", 2000);
		response = wishlistControl.addToWishlist(wishlist);
		assertEquals(1, response.getStatus());
	}
	
	@Test
	void addtowishlisttestfailcase() {
		Wishlist wishlist = new Wishlist(null, "tractor", "farmtrac", "cultivating", "s@n.com", "pune", 2000);
		response = wishlistControl.addToWishlist(wishlist);
		assertEquals(0, response.getStatus());
	}
	
	@Test
	void getwishlist() {
		User user = new User();
		List<Wishlist> l = new ArrayList<Wishlist>();
		user.setEmail("v@g.com");
		l = wishlistControl.getWishlist(user.getEmail());
		assertEquals(3, l.size());
	}

	@Test
	void getwishlistfailcase() {
		User user = new User();
		List<Wishlist> l = new ArrayList<Wishlist>();
		user.setEmail("v@g.co");
		l = wishlistControl.getWishlist(user.getEmail());
		assertEquals(0, l.size());
	}
}
