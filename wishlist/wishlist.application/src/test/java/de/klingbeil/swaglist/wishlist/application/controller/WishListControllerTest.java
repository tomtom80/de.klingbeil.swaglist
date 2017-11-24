package de.klingbeil.swaglist.wishlist.application.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import de.klingbeil.swaglist.wishlist.api.model.WishItemDto;
import de.klingbeil.swaglist.wishlist.application.repository.WishListService;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

public class WishListControllerTest<S> {

  private static final String NAME = "testItem";
  private static final String ID = "testid";
  private static final String ROOT_URL = "http://localhost";

  @Mock
  private WishListService repository;
  @Mock
  private ModelMapper modelMapper;
  @Mock
  private WishItem.Builder wishItemBuilder;

  private WishListController controller;
  private WishItem wishItem;
  private WishItemDto wishItemDto;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    controller = new WishListController();
    controller.service = repository;
    controller.modelMapper = modelMapper;

    wishItem = new WishItem.Builder(ID,NAME).build();
    wishItemDto = createWishItemDto();
  }


  @Test
  public void testfindAll() throws Exception {
    List<WishItem> wishes = Arrays.asList(wishItem);
    when(this.repository.findAll()).thenReturn(wishes);
    when( this.modelMapper.map(wishItem, WishItemDto.class)).thenReturn(wishItemDto);

    List<WishItemDto> items = controller.findAll();

    assertEquals(1, items.size());
    assertEquals(this.wishItemDto, items.get(0));
  }

  @Test
  public void testCreate() throws Exception {
    when(this.repository.create(wishItem)).thenReturn(wishItem);
    when( this.modelMapper.map(wishItemDto, WishItem.Builder.class)).thenReturn(wishItemBuilder);
    when( this.wishItemBuilder.build()).thenReturn(wishItem);
    
    ResponseEntity<Void> response = controller.create(wishItemDto);

    assertEquals(ROOT_URL +"/"+ ID, response.getHeaders().getLocation().toString());
  }


  @Test
  public void testCreateAndFind() throws Exception {
    when(this.repository.findOne(ID)).thenReturn(wishItem);
    when( this.modelMapper.map(wishItem, WishItemDto.class)).thenReturn(wishItemDto);

    assertSame(wishItemDto, controller.findOne(ID));
  }

  private WishItemDto createWishItemDto() {
    WishItemDto dto = new WishItemDto();
    dto.setId(ID);
    dto.setName(NAME);
    return dto;
  }

}
