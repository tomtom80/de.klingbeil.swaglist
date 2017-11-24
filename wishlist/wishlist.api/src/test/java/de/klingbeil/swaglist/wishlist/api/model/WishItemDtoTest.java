package de.klingbeil.swaglist.wishlist.api.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import de.klingbeil.swaglist.wishlist.api.model.converter.WishItemDtoToWishItemConverter;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

public class WishItemDtoTest {

  private static final String ID = "id";
  private static final String NAME = "name";
  private static final String DESCRIPTION = "description";
  
  private ModelMapper modelMapper = new ModelMapper();
  
  @Before
  public void setUp() throws Exception {
    modelMapper.addMappings(new WishItemDtoToWishItemConverter());
  }

  @Test
  public void testConvertItemToItemDto() {
    WishItem item = new WishItem.Builder(ID, NAME).description(DESCRIPTION).build();

    WishItemDto itemDto = modelMapper.map(item, WishItemDto.class);

    assertEquals(ID, itemDto.getId());
    assertEquals(NAME, itemDto.getName());
    assertEquals(DESCRIPTION, itemDto.getDescription());
  }
  @Test
  public void testConvertItemDtoToItem() {
    WishItemDto itemDto = new WishItemDto();
    itemDto.setId(ID);
    itemDto.setName(NAME);
    itemDto.setDescription(DESCRIPTION);

    WishItem item = modelMapper.map(itemDto, WishItem.Builder.class).build();
    
    assertEquals(ID, item.getId());
    assertEquals(NAME, item.getName());
    assertEquals(DESCRIPTION, item.getDescription());
  }
  
  
  @Test
  public void testSetters() {
    WishItemDto itemDto = new WishItemDto();
    itemDto.setId(ID);
    itemDto.setName(NAME);
    itemDto.setDescription(DESCRIPTION);

    assertEquals(ID, itemDto.getId());
    assertEquals(NAME, itemDto.getName());
    assertEquals(DESCRIPTION, itemDto.getDescription());
  }
}
