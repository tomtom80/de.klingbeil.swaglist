package de.klingbeil.wishlist.wishes.command;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.apache.commons.validator.routines.UrlValidator;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import de.klingbeil.wishlist.core.api.wishes.AddWishToWishlistCommand;
import de.klingbeil.wishlist.core.api.wishes.WishId;
import de.klingbeil.wishlist.core.api.wishes.WishNameAddedEvent;
import de.klingbeil.wishlist.core.api.wishes.WishUpdateCommand;
import de.klingbeil.wishlist.core.api.wishes.WishUpdatedEvent;
import de.klingbeil.wishlist.core.api.wishes.WishUrlAddedEvent;
import de.klingbeil.wishlist.core.api.wishlist.WishlistId;

@Aggregate
public class Wish {

	@AggregateIdentifier
	private WishId wishId;

	@SuppressWarnings("unused")
	private WishlistId wishlistId;
	@SuppressWarnings("unused")
	private String wishName;
	@SuppressWarnings("unused")
	private String wishDescription;
	@SuppressWarnings("unused")
	private String wishLocation;

	public Wish() {
		// Required by Axon Framework
	}

	@CommandHandler
	public Wish(AddWishToWishlistCommand command) {
		UrlValidator urlValidator = new UrlValidator();
		String wishText = command.getWishText();
		if (urlValidator.isValid(wishText)) {
			apply(new WishUrlAddedEvent(command.getWishId(), command.getWishlistId(), wishText));
		} else {
			apply(new WishNameAddedEvent(command.getWishId(), command.getWishlistId(), wishText));
		}
	}

	@CommandHandler
	public void handle(WishUpdateCommand command) {
		apply(new WishUpdatedEvent(wishId, command.getWishlistId(), command.getWishName(), command.getWishDescription(),
				command.getWishLocation()));
	}

	@EventSourcingHandler
	public void on(WishNameAddedEvent event) {
		wishId = event.getWishId();
		wishlistId = event.getWishlistId();
		wishName = event.getWishName();
	}

	@EventSourcingHandler
	public void on(WishUrlAddedEvent event) {
		wishId = event.getWishId();
		wishlistId = event.getWishlistId();
	}

	@EventSourcingHandler
	public void on(WishUpdatedEvent event) {
		wishName = event.getWishName();
		wishDescription = event.getWishDescrition();
		wishLocation = event.getWishLocation();
	}

}
