package de.klingbeil.swaglist.wishlist.application.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import de.klingbeil.swaglist.wishlist.application.repository.WishListRepository;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

public class WishListControllerTest {

  @Mock
  private WishListRepository repository;
  private WishListController controller;
  private WishItem wishItem;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    controller = new WishListController();
    controller.repository = repository;
    wishItem = new WishItem.Builder("testItem").build();
  }


  @Test
  public void testfindAll() throws Exception {
    List<WishItem> wishes = Arrays.asList(wishItem);
    when(this.repository.findAll()).thenReturn(wishes);

    List<WishItem> items = controller.findAll();

    assertEquals(wishes, items);
  }

}
