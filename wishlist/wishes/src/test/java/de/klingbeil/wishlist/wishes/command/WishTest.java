package de.klingbeil.wishlist.wishes.command;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

import de.klingbeil.wishlist.core.api.wishes.AddWishToWishlistCommand;
import de.klingbeil.wishlist.core.api.wishes.WishId;
import de.klingbeil.wishlist.core.api.wishes.WishNameAddedEvent;
import de.klingbeil.wishlist.core.api.wishes.WishUrlAddedEvent;
import de.klingbeil.wishlist.core.api.wishlist.WishlistId;

public class WishTest {

	private static final String WISH_URL = "http://webshop.com";
	private static final String WISH_NAME = "wishName";
	private AggregateTestFixture<Wish> aggregateTestFixture;
	private WishId wishId;
	private WishlistId wishlistId;

	@Before
	public void setUp() throws Exception {
		aggregateTestFixture = new AggregateTestFixture<>(Wish.class);
		wishId = new WishId();
		wishlistId = new WishlistId();
	}

	@Test
	public void testAddWishNameToWishlist() {
		aggregateTestFixture.givenNoPriorActivity().when(new AddWishToWishlistCommand(wishId, wishlistId, WISH_NAME))
				.expectEvents(new WishNameAddedEvent(wishId, wishlistId, WISH_NAME));
	}

	@Test
	public void testAddWishUrlToWishlist() {
		aggregateTestFixture.givenNoPriorActivity().when(new AddWishToWishlistCommand(wishId, wishlistId, WISH_URL))
				.expectEvents(new WishUrlAddedEvent(wishId, wishlistId, WISH_URL));
	}

}
