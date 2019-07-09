package de.klingbeil.wishlist.wishes.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist/{wishlistId}/wish")
public class WishQueryController {

	private WishEventHandler eventHandler;

	@Autowired
	public WishQueryController(WishEventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	@GetMapping
	public List<WishView> getWishesForWishlist(@PathVariable String wishlistId) {
		return eventHandler.getWishesForWishlist(wishlistId);
	}
}
