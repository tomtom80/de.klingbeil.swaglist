package de.klingbeil.swaglist.wishlist.domain;

import static org.junit.Assert.*;
import org.junit.Test;

public class WishItemTest {
  private static final String ID = "id";
  private static final String DESCRIPTION = "description";
  private static final String NAME = "name";

  @Test
  public void testName() {

    WishItem item = new WishItem.Builder(ID, NAME).build();

    assertEquals(ID, item.getId());
    assertEquals(NAME, item.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNameNull() throws Exception {
    new WishItem.Builder(ID, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIdNull() throws Exception {
    new WishItem.Builder(null, NAME);
  }

  @Test
  public void testDescription() throws Exception {

    WishItem item = new WishItem.Builder(ID, NAME).description(DESCRIPTION).build();

    assertEquals(DESCRIPTION, item.getDescription());
  }
  
  @Test
  public void testId() throws Exception {
    
    WishItem item = new WishItem.Builder(ID, NAME).build();

    assertEquals(ID, item.getId());
    assertEquals(NAME, item.getName());
  }

}
