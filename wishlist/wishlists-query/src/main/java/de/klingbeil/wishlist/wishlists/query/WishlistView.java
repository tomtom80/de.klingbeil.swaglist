package de.klingbeil.wishlist.wishlists.query;

import java.io.Serializable;
import javax.persistence.Entity;
import org.springframework.data.annotation.Id;
import de.klingbeil.wishlist.core.api.wishlist.WishlistType;

@Entity
public class WishlistView implements Serializable {

  private static final long serialVersionUID = -3821373143662422327L;
  @Id
  @javax.persistence.Id
  private String identifier;
  private String wishlistName;
  private WishlistType wishlistType;

  public WishlistView() {
    // Required by Axon Framework
  }

  public WishlistView(String identifier, String wishlistName, WishlistType wishlistType) {
    super();
    this.identifier = identifier;
    this.wishlistName = wishlistName;
    this.wishlistType = wishlistType;
  }

  public String getIdentifier() {
    return identifier;
  }

  public String getWishlistName() {
    return wishlistName;
  }

  public WishlistType getWishlistType() {
    return wishlistType;
  }

}
