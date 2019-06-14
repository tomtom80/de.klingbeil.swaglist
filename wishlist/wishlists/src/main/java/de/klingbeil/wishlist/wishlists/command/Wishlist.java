package de.klingbeil.wishlist.wishlists.command;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import de.klingbeil.wishlist.core.api.wishlist.CreateWishlistCommand;
import de.klingbeil.wishlist.core.api.wishlist.DeleteWishlistCommand;
import de.klingbeil.wishlist.core.api.wishlist.UpdateWishlistCommand;
import de.klingbeil.wishlist.core.api.wishlist.WishlistCreatedEvent;
import de.klingbeil.wishlist.core.api.wishlist.WishlistDeletedEvent;
import de.klingbeil.wishlist.core.api.wishlist.WishlistId;
import de.klingbeil.wishlist.core.api.wishlist.WishlistType;
import de.klingbeil.wishlist.core.api.wishlist.WishlistUpdatedEvent;

@Aggregate
public class Wishlist {

	@AggregateIdentifier
	private WishlistId wishlistId;
	@SuppressWarnings("unused")
	private String wishlistName;
	@SuppressWarnings("unused")
	private WishlistType wishlistType;

	public Wishlist() {
		// Required by Axon Framework
	}

	@CommandHandler
	public Wishlist(CreateWishlistCommand command) {
		apply(new WishlistCreatedEvent(command.getWishlistId(), command.getWishlistName(), command.getWishlistType()));
	}

	@CommandHandler
	public void handle(UpdateWishlistCommand command) {
		apply(new WishlistUpdatedEvent(wishlistId, command.getWishlistName(), command.getWishlistType()));
	}

	@CommandHandler
	public void handle(DeleteWishlistCommand command) {
		apply(new WishlistDeletedEvent(wishlistId));
	}

	@EventSourcingHandler
	protected void on(WishlistCreatedEvent event) {
		wishlistId = event.getWishlistId();
		wishlistName = event.getWishlistName();
		wishlistType = event.getWishlistType();
	}

	@EventSourcingHandler
	protected void on(WishlistUpdatedEvent event) {
		wishlistName = event.getWishlistName();
		wishlistType = event.getWishlistType();
	}

	@EventSourcingHandler
	protected void on(WishlistDeletedEvent event) {
		markDeleted();
	}
}
