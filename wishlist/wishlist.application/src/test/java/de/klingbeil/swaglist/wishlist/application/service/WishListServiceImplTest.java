package de.klingbeil.swaglist.wishlist.application.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import de.klingbeil.swaglist.wishlist.domain.WishItem;
import de.klingbeil.swaglist.wishlist.persistence.model.WishItemModel;
import de.klingbeil.swaglist.wishlist.persistence.repository.WishItemRepository;

public class WishListServiceImplTest {

  private static final String ID = "ID";
  @Mock
  private WishItemRepository wishItemRepository;
  @Mock
  private ModelMapper modelMapper;
  @Mock
  private WishItem.Builder wishItemBuilder;
  @Mock
  private WishItem wishItem;

  private WishListServiceImpl service;
  private WishItemModel wishItemModel;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    service = new WishListServiceImpl();
    service.wishItemRepository = wishItemRepository;
    service.modelMapper = modelMapper;
    wishItemModel = new WishItemModel();
  }

  @Test
  public void testFindAll() {
    when(this.wishItemRepository.findAll()).thenReturn(Arrays.asList(wishItemModel));
    mockModelToItemConversion();

    List<WishItem> result = service.findAll();

    assertEquals(1, result.size());
    assertEquals(wishItem, result.get(0));
  }

  @Test
  public void testFindOne() throws Exception {
    when(this.wishItemRepository.findOne(ID)).thenReturn(wishItemModel);
    mockModelToItemConversion();

    WishItem result = service.findOne(ID);

    assertEquals(wishItem, result);
  }

  @Test
  public void testCreate() throws Exception {
    when(this.modelMapper.map(wishItem, WishItemModel.class)).thenReturn(wishItemModel);
    when(this.wishItemRepository.save(wishItemModel)).thenReturn(wishItemModel);
    mockModelToItemConversion();

    WishItem result = service.persist(wishItem);

    assertEquals(wishItem, result);
  }

  @Test
  public void testDelete() throws Exception {
    when(this.modelMapper.map(wishItem, WishItemModel.class)).thenReturn(wishItemModel);

    service.delete(wishItem);

    verify(this.wishItemRepository).delete(wishItemModel);
  }

  private void mockModelToItemConversion() {
    when(this.modelMapper.map(wishItemModel, WishItem.Builder.class)).thenReturn(wishItemBuilder);
    when(this.wishItemBuilder.build()).thenReturn(wishItem);
  }

}
