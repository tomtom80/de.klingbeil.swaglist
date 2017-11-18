package de.klingbeil.swaglist.wishlist.application.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import de.klingbeil.swaglist.wishlist.application.repository.WishListRepository;
@Configuration
public class TestConfig {

  @Bean
  public WishListRepository repository() {
    return new WishListRepository();
  }
}
