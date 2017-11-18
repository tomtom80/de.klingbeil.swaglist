package de.klingbeil.swaglist.wishlist.application.controller;

import org.junit.Before;
import org.junit.Test;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class WishListControllerIntegrationtest {

  @Before
  public void setUp() {
    RestAssuredMockMvc.standaloneSetup(new WishListController());
  }


  @Test
  public void testFindAll() {

    RestAssuredMockMvc.given().standaloneSetup(new WishListController()).when().get("/wishlist")
        .then().statusCode(200);
  }

}
