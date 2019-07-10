package de.klingbeil.wishlist.wishes.query;

import java.net.URL;
import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.klingbeil.wishlist.core.api.wishes.WishNameAddedEvent;
import de.klingbeil.wishlist.core.api.wishes.WishRemovedFromWishlistEvent;
import de.klingbeil.wishlist.core.api.wishes.WishUpdatedEvent;
import de.klingbeil.wishlist.core.api.wishes.WishUrlAddedEvent;

@Service
@ProcessingGroup("wishes")
public class WishEventHandler {

	private WishViewRepository wishRepository;

	@Autowired
	public WishEventHandler(WishViewRepository wishRepository) {
		this.wishRepository = wishRepository;
	}

	@EventHandler
	public void on(WishNameAddedEvent event) {
		wishRepository.save(new WishView(event.getWishId().getIdentifier(), event.getWishlistId().getIdentifier(),
				event.getWishName(), null, null, null, null));
	}

	@EventHandler
	public void on(WishUrlAddedEvent event) {
		wishRepository.save(new WishView(event.getWishId().getIdentifier(), event.getWishlistId().getIdentifier(),
				event.getWishUrl(), null, null, event.getWishUrl(), null));
	}

	@EventHandler
	public void on(WishUpdatedEvent event) {
		URL wishImageUrl = event.getWishImageUrl();
		URL wishLocationUrl = event.getWishLocationUrl();
		wishRepository.save(new WishView(event.getWishId().getIdentifier(), event.getWishlistId().getIdentifier(),
				event.getWishName(), event.getWishDescrition(), event.getWishLocation(),
				wishLocationUrl != null ? wishLocationUrl.toString() : null,
				wishImageUrl != null ? wishImageUrl.toString() : null));
	}

	@EventHandler
	public void on(WishRemovedFromWishlistEvent event) {
		wishRepository.deleteByIdentifierAndWishlistId(event.getWishId().getIdentifier(),
				event.getWishlistId().getIdentifier());
	}

	public List<WishView> getWishesForWishlist(String wishlistId) {
		return wishRepository.findByWishlistId(wishlistId);
	}

}
