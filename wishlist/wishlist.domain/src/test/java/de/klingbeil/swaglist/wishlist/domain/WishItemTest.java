package de.klingbeil.swaglist.wishlist.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import de.klingbeil.swaglist.wishlist.domain.WishItem;

public class WishItemTest {

	private static final String DESCRIPTION = "description";
	private static final String NAME = "name";

	@Test
	public void testName() {

		WishItem item = new WishItem.Builder(NAME).build();

		assertEquals(NAME, item.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNameNull() throws Exception {
		new WishItem.Builder(null);
	}

	@Test
	public void testDescription() throws Exception {

		WishItem item = new WishItem.Builder(NAME).description(DESCRIPTION).build();

		assertEquals(DESCRIPTION, item.getDescription());
	}

}
