package de.klingbeil.swaglist.wishlist.application.controller;

import java.util.List;
import de.klingbeil.swaglist.wishlist.application.repository.WishListRepository;
import de.klingbeil.swaglist.wishlist.domain.WishItem;

public class WishListController {

  WishListRepository repository;

  public List<WishItem> findAll() {
    return repository.findAll();
  }

}
