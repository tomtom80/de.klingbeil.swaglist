package de.klingbeil.swaglist.wishlist.application.controller;

import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import de.klingbeil.swaglist.wishlist.application.repository.WishListRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(WishListController.class)
@ContextConfiguration(classes = TestConfig.class)
public class WishListControllerIntegrationtest {

  @MockBean
  private WishListRepository repository;

  @Before
  public void setUp() {
    RestAssuredMockMvc.standaloneSetup(new WishListController());
  }


  @Test
  public void testFindAll() {
    when(this.repository.findAll()).thenReturn(new WishListRepository().findAll());

    RestAssuredMockMvc.given().standaloneSetup(new WishListController()).when().get("/wishlist")
        .then().statusCode(200);
  }

}
