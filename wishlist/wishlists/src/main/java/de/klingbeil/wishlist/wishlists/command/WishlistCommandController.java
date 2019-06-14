package de.klingbeil.wishlist.wishlists.command;

import java.util.concurrent.Future;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.klingbeil.wishlist.core.api.users.UserId;
import de.klingbeil.wishlist.core.api.wishlist.CreateWishlistCommand;
import de.klingbeil.wishlist.core.api.wishlist.DeleteWishlistCommand;
import de.klingbeil.wishlist.core.api.wishlist.UpdateWishlistCommand;
import de.klingbeil.wishlist.core.api.wishlist.WishlistId;
import de.klingbeil.wishlist.core.api.wishlist.WishlistType;

@RestController
public class WishlistCommandController {
	private final CommandGateway commandGateway;

	public WishlistCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping("/wishlist")
	public Future<String> createWishlist(
			@RequestBody @Valid de.klingbeil.wishlist.wishlists.command.dto.Wishlist wishlist) {
		Assert.notNull(wishlist.getName(), "name is mandatory for a wishlist");
		return commandGateway.send(
				new CreateWishlistCommand(new WishlistId(), new UserId(), wishlist.getName(), WishlistType.PRIVAT));
	}

	@PutMapping("/wishlist/{wishlistId}")
	public Future<String> updateWishlist(@PathVariable String wishlistId,
			@RequestBody @Valid de.klingbeil.wishlist.wishlists.command.dto.Wishlist wishlist) {
		Assert.notNull(wishlist.getName(), "name is mandatory for a wishlist");
		return commandGateway
				.send(new UpdateWishlistCommand(new WishlistId(wishlistId), wishlist.getName(), WishlistType.PRIVAT));
	}

	@DeleteMapping("/wishlist/{wishlistId}")
	public Future<String> deleteWishlist(@PathVariable String wishlistId) {
		return commandGateway.send(new DeleteWishlistCommand(new WishlistId(wishlistId)));
	}
}
