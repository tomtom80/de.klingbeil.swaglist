package de.klingbeil.swaglist.wishlist.application.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import de.klingbeil.swaglist.wishlist.api.model.WishItemDto;
import de.klingbeil.swaglist.wishlist.application.service.WishListService;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

public class WishListControllerTest<S> {

  private static final String NAME = "testItem";
  private static final String ID = "testid";
  private static final String ROOT_URL = "http://localhost";

  @Mock
  private WishListService service;
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
    controller.service = service;
    controller.modelMapper = modelMapper;

    wishItem = new WishItem.Builder(ID, NAME).build();
    wishItemDto = createWishItemDto();
  }


  @Test
  public void testfindAll() throws Exception {
    List<WishItem> wishes = Arrays.asList(wishItem);
    when(this.service.findAll()).thenReturn(wishes);
    when(this.modelMapper.map(wishItem, WishItemDto.class)).thenReturn(wishItemDto);

    ResponseEntity<List<WishItemDto>> response = controller.findAll();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().size());
    assertEquals(this.wishItemDto, response.getBody().get(0));
  }

  @Test
  public void testFindOne() throws Exception {
    when(this.service.findOne(ID)).thenReturn(wishItem);
    when(this.modelMapper.map(wishItem, WishItemDto.class)).thenReturn(wishItemDto);

    ResponseEntity<WishItemDto> response = controller.findOne(ID);
    
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertSame(wishItemDto, response.getBody());
  }


  @Test
  public void testCreate() throws Exception {
    when(this.modelMapper.map(wishItemDto, WishItem.Builder.class)).thenReturn(wishItemBuilder);
    when(this.wishItemBuilder.build()).thenReturn(wishItem);
    when(this.service.persist(wishItem)).thenReturn(wishItem);
    when(this.modelMapper.map(wishItem, WishItemDto.class)).thenReturn(wishItemDto);

    ResponseEntity<WishItemDto> response = controller.create(wishItemDto);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(ROOT_URL + "/" + ID, response.getHeaders().getLocation().toString());
    assertEquals(NAME, response.getBody().getName());
  }


  @Test
  public void testUpdate() throws Exception {
    when(this.modelMapper.map(wishItemDto, WishItem.Builder.class)).thenReturn(wishItemBuilder);
    when(this.wishItemBuilder.build()).thenReturn(wishItem);
    when(this.service.findOne(ID)).thenReturn(wishItem);
    when(this.service.persist(any(WishItem.class))).thenReturn(wishItem);
    when(this.modelMapper.map(wishItem, WishItemDto.class)).thenReturn(wishItemDto);

    ResponseEntity<WishItemDto> response = controller.update(ID, wishItemDto);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(NAME, response.getBody().getName());
  }
  
  @Test
  public void testDelete() throws Exception {
    when(this.service.findOne(ID)).thenReturn(wishItem);

    ResponseEntity<Void> response = controller.delete(ID);
    
    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify( this.service).delete( wishItem);
  }

  private WishItemDto createWishItemDto() {
    WishItemDto dto = new WishItemDto();
    dto.setId(ID);
    dto.setName(NAME);
    return dto;
  }

}
