package de.klingbeil.wishlist.wishlists.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;

import de.klingbeil.wishlist.core.api.wishlist.WishlistCreatedEvent;
import de.klingbeil.wishlist.core.api.wishlist.WishlistId;
import de.klingbeil.wishlist.core.api.wishlist.WishlistType;

@RunWith(MockitoJUnitRunner.class)
public class WishEventHandlerTest {

	private final WishlistViewRepository wishlistListViewRepository = mock(WishlistViewRepository.class);

	private WishlistEventHandler testSubject;

	@Before
	public void setUp() {
		testSubject = new WishlistEventHandler(wishlistListViewRepository);
	}

	@Test
	public void testOnWishlistCreatedEventAnWishlistListViewIsSaved() {
		WishlistId expectedWishlistId = new WishlistId();
		String expectedName = "name";
		WishlistType expectedType = WishlistType.PUBLIC;
		WishlistCreatedEvent testEvent = new WishlistCreatedEvent(expectedWishlistId, expectedName, expectedType);

		testSubject.on(testEvent);

		ArgumentCaptor<WishlistView> listViewCaptor = ArgumentCaptor.forClass(WishlistView.class);
		verify(wishlistListViewRepository).save(listViewCaptor.capture());
		WishlistView result = listViewCaptor.getValue();
		assertNotNull(result);
		assertEquals(expectedWishlistId.getIdentifier(), result.getIdentifier());
		assertEquals(expectedName, result.getWishlistName());
		assertEquals(expectedType, result.getWishlistType());
	}
}