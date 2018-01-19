package de.klingbeil.swaglist.wishlist.application.service;

import java.util.List;
import de.klingbeil.swaglist.wishlist.domain.WishItem;


public interface WishListService {

  List<WishItem> findAll();

  WishItem findOne(String id);

  WishItem persist(WishItem wishItem);

  void delete(WishItem wishItem);

}
