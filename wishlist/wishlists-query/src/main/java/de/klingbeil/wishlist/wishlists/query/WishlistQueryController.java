package de.klingbeil.wishlist.wishlists.query;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistQueryController {

  private WishlistEventHandler eventHandler;

  @Autowired
  public WishlistQueryController(WishlistEventHandler eventHandler) {
    this.eventHandler = eventHandler;
  }

  @GetMapping
  public List<WishlistView> getWishlists() {
    return eventHandler.getWishlists();
  }
}
