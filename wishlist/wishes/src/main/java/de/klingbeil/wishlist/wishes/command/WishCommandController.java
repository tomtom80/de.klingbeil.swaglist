package de.klingbeil.wishlist.wishes.command;

import java.util.concurrent.Future;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.klingbeil.wishlist.core.api.wishes.AddWishToWishlistCommand;
import de.klingbeil.wishlist.core.api.wishes.WishId;
import de.klingbeil.wishlist.core.api.wishlist.WishlistId;

@RestController
public class WishCommandController {
	private final CommandGateway commandGateway;

	public WishCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping("/wishlist/{wishlistId}/wish")
	public Future<String> addWishToWishlist(@PathVariable String wishlistId, @RequestBody @Valid String text) {
		Assert.notNull(wishlistId, "wishlistId is mandatory for a wish");
		return commandGateway.send(new AddWishToWishlistCommand(new WishId(), new WishlistId(wishlistId), text));
	}

}
