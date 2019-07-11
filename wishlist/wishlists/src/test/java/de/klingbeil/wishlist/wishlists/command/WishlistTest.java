package de.klingbeil.wishlist.wishlists.command;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import de.klingbeil.wishlist.core.api.users.UserId;
import de.klingbeil.wishlist.core.api.wishlist.CreateWishlistCommand;
import de.klingbeil.wishlist.core.api.wishlist.DeleteWishlistCommand;
import de.klingbeil.wishlist.core.api.wishlist.UpdateWishlistCommand;
import de.klingbeil.wishlist.core.api.wishlist.WishlistCreatedEvent;
import de.klingbeil.wishlist.core.api.wishlist.WishlistDeletedEvent;
import de.klingbeil.wishlist.core.api.wishlist.WishlistId;
import de.klingbeil.wishlist.core.api.wishlist.WishlistType;
import de.klingbeil.wishlist.core.api.wishlist.WishlistUpdatedEvent;

@RunWith(JUnit4.class)
public class WishlistTest {

  private static final String WISHLIST_NAME = "FamilyWishlist";

  private AggregateTestFixture<Wishlist> testFixture;

  private WishlistId wishlistId = new WishlistId();
  private WishlistCreatedEvent wishlistCreatedEvent;

  @Before
  public void setUp() throws Exception {
    testFixture = new AggregateTestFixture<>(Wishlist.class);

    wishlistCreatedEvent = new WishlistCreatedEvent(wishlistId, WISHLIST_NAME, WishlistType.PRIVAT);
  }

  @Test
  public void testCreateWishlist() {
    testFixture.givenNoPriorActivity()
        .when(
            new CreateWishlistCommand(wishlistId, new UserId(), WISHLIST_NAME, WishlistType.PRIVAT))
        .expectEvents(wishlistCreatedEvent);
  }

  @Test
  public void testUpdateWishlist() {
    testFixture.given(wishlistCreatedEvent)
        .when(new UpdateWishlistCommand(wishlistId, "MyWishlist", WishlistType.PUBLIC))
        .expectEvents(new WishlistUpdatedEvent(wishlistId, "MyWishlist", WishlistType.PUBLIC));
  }

  @Test
  public void testDeleteWishlist() {
    testFixture.given(wishlistCreatedEvent).when(new DeleteWishlistCommand(wishlistId))
        .expectEvents(new WishlistDeletedEvent(wishlistId));
  }
}
