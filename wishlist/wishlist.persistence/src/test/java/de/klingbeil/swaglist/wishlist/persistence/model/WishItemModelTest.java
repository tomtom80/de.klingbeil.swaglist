package de.klingbeil.swaglist.wishlist.persistence.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import de.klingbeil.swaglist.wishlist.domain.WishItem;
import de.klingbeil.swaglist.wishlist.persistence.model.converter.WishItemModelToWishItemConverter;

public class WishItemModelTest {

  private static final String ID = "id";
  private static final String DESCRIPTION = "description";
  private static final String NAME = "name";
  private ModelMapper modelMapper;

  @Before
  public void setUp() throws Exception {
    modelMapper = new ModelMapper();
    modelMapper.addMappings(new WishItemModelToWishItemConverter());
  }

  @Test
  public void testWishItem() {
    WishItemModel wishItemModel = new WishItemModel();

    wishItemModel.setId(ID);
    wishItemModel.setDescription(DESCRIPTION);
    wishItemModel.setName(NAME);

    assertEquals(ID, wishItemModel.getId());
    assertEquals(NAME, wishItemModel.getName());
    assertEquals(DESCRIPTION, wishItemModel.getDescription());
  }

  @Test
  public void testConversionItemToModel() throws Exception {
    WishItem wishItem = new WishItem.Builder().description(DESCRIPTION).id(ID).name(NAME).build();

    WishItemModel itemModel = modelMapper.map(wishItem, WishItemModel.class);

    assertEquals(ID, itemModel.getId());
    assertEquals(NAME, itemModel.getName());
    assertEquals(DESCRIPTION, itemModel.getDescription());
  }

  @Test
  public void testConversionModelToItem() throws Exception {
    WishItemModel wishItemModel = new WishItemModel();
    wishItemModel.setId(ID);
    wishItemModel.setDescription(DESCRIPTION);
    wishItemModel.setName(NAME);

    WishItem item = modelMapper.map(wishItemModel, WishItem.Builder.class).build();

    assertEquals(ID, item.getId());
    assertEquals(NAME, item.getName());
    assertEquals(DESCRIPTION, item.getDescription());
  }

}
