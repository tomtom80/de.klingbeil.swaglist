package de.klingbeil.swaglist.wishlist.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"de.klingbeil.swaglist"})
public class WishListApplication {

  public static void main(String[] args) {
    SpringApplication.run(WishListApplication.class, args);
  }
}
