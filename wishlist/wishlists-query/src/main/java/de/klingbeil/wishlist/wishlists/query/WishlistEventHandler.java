package de.klingbeil.wishlist.wishlists.query;

import java.util.List;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.klingbeil.wishlist.core.api.wishlist.WishlistCreatedEvent;
import de.klingbeil.wishlist.core.api.wishlist.WishlistDeletedEvent;
import de.klingbeil.wishlist.core.api.wishlist.WishlistUpdatedEvent;

@Service
@ProcessingGroup("wishlist")
public class WishlistEventHandler {

  private final WishlistViewRepository wishlistRepository;

  @Autowired
  public WishlistEventHandler(WishlistViewRepository wishlistRepository) {
    this.wishlistRepository = wishlistRepository;
  }

  @EventHandler
  public void on(WishlistCreatedEvent event) {
    wishlistRepository.save(new WishlistView(event.getWishlistId().getIdentifier(),
        event.getWishlistName(), event.getWishlistType()));
  }

  @EventHandler
  public void on(WishlistUpdatedEvent event) {
    wishlistRepository.save(new WishlistView(event.getWishlistId().getIdentifier(),
        event.getWishlistName(), event.getWishlistType()));
  }

  @EventHandler
  public void on(WishlistDeletedEvent event) {
    WishlistView wishlistListView =
        wishlistRepository.getOne(event.getWishlistId().getIdentifier());
    wishlistRepository.delete(wishlistListView);
  }

  public List<WishlistView> getWishlists() {
    return wishlistRepository.findAll();
  }

}
