package de.klingbeil.swaglist.wishlist.application.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.UUID;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.klingbeil.swaglist.wishlist.api.model.WishItemDto;
import de.klingbeil.swaglist.wishlist.application.service.WishListService;
import de.klingbeil.swaglist.wishlist.domain.WishItem;
import de.klingbeil.swaglist.wishlist.domain.WishItem.Builder;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WishListController.class, secure = false)
public class WishListControllerIntegrationtest {

  @MockBean
  private WishListService service;
  @MockBean
  private ModelMapper modelMapper;
  @Resource
  private MockMvc mvc;
  @Resource
  private ObjectMapper mapper;
  private WishItem wishItem;
  private WishItemDto wishItemDto;
  private Builder wishItemBuilder;

  @Before
  public void setUp() throws Exception {
    String id = UUID.randomUUID().toString();
    String name = "iPhone";
    String description = "The new iPhone X";
    wishItemBuilder = new WishItem.Builder().id(id).name(name).description(description);
    wishItem = wishItemBuilder.build();
    wishItemDto = createWishItemDto(id, name, description);
  }

  @Test
  public void testFindAll() throws Exception {
    given(service.findAll()).willReturn(Arrays.asList(wishItem));
    given(modelMapper.map(wishItem, WishItemDto.class)).willReturn(wishItemDto);

    mvc.perform(get("/wishlist")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(wishItemDto.getId())))
        .andExpect(jsonPath("$[0].name", is(wishItemDto.getName())))
        .andExpect(jsonPath("$[0].description", is(wishItemDto.getDescription())));
  }

  @Test
  public void testFindById() throws Exception {
    given(service.findOne(wishItem.getId())).willReturn(wishItem);
    given(modelMapper.map(wishItem, WishItemDto.class)).willReturn(wishItemDto);

    mvc.perform(get("/wishlist/{id}", wishItem.getId())).andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(wishItemDto.getId())))
        .andExpect(jsonPath("$.name", is(wishItemDto.getName())))
        .andExpect(jsonPath("$.description", is(wishItemDto.getDescription())));
  }

  @Test
  public void testCreate() throws Exception {
    String json = mapper.writeValueAsString(wishItemDto);
    given(modelMapper.map(any(WishItemDto.class), eq(WishItem.Builder.class)))
        .willReturn(wishItemBuilder);
    given(service.persist(any(WishItem.class))).willReturn(wishItem);
    given(modelMapper.map(wishItem, WishItemDto.class)).willReturn(wishItemDto);

    mvc.perform(post("/wishlist").content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "http://localhost/wishlist/" + wishItem.getId()))
        .andExpect(jsonPath("$.id", is(wishItem.getId())))
        .andExpect(jsonPath("$.name", is(wishItemDto.getName())))
        .andExpect(jsonPath("$.description", is(wishItemDto.getDescription())));
  }

  @Test
  public void testUpdate() throws Exception {
    String id = wishItem.getId();
    String json = mapper.writeValueAsString(wishItemDto);
    given(modelMapper.map(any(WishItemDto.class), eq(WishItem.Builder.class)))
        .willReturn(wishItemBuilder);
    given(service.findOne(id)).willReturn(wishItem);
    given(service.persist(any(WishItem.class))).willReturn(wishItem);
    given(modelMapper.map(wishItem, WishItemDto.class)).willReturn(wishItemDto);

    mvc.perform(put("/wishlist/{id}", id).content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(jsonPath("$.id", is(id)))
        .andExpect(jsonPath("$.name", is(wishItemDto.getName())))
        .andExpect(jsonPath("$.description", is(wishItemDto.getDescription())));
  }

  @Test
  public void testDelete() throws Exception {
    given(service.findOne(wishItem.getId())).willReturn(wishItem);
    given(service.persist(any(WishItem.class))).willReturn(wishItem);
    given(modelMapper.map(any(WishItemDto.class), eq(WishItem.Builder.class)))
        .willReturn(wishItemBuilder);

    mvc.perform(delete("/wishlist/{id}", wishItem.getId())).andExpect(status().isOk());

    verify(service).delete(wishItem);
  }

  private static WishItemDto createWishItemDto(String id, String name, String description) {
    WishItemDto dto = new WishItemDto();
    dto.setId(id);
    dto.setName(name);
    dto.setDescription(description);
    return dto;
  }
}
